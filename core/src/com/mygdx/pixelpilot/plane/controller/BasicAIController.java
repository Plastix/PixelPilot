package com.mygdx.pixelpilot.plane.controller;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.controller.ai.ProximityFinder;
import com.mygdx.pixelpilot.screen.game.World;
import com.mygdx.pixelpilot.util.Utils;

public class BasicAIController extends AIController {

    private final float detectionDistance = 500;
    private final float trackingDistance = 1500;
    private Plane plane;
    private ProximityFinder proximity;
    private SteeringAcceleration<Vector2> accel = new SteeringAcceleration<Vector2>(new Vector2());
    private StateMachine<BasicAIController> stateMachine;

    public BasicAIController() {
        this.stateMachine = new DefaultStateMachine<BasicAIController>(this, BasicAIControllerState.INIT);
        this.proximity = new ProximityFinder(detectionDistance);
    }

    @Override
    public void control(Plane planeBody) {
        this.plane = planeBody;
        stateMachine.update();
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);
        float wallAvoidanceBuffer = 150;
        this.worldBounds = new Rectangle(
                worldBounds.x + wallAvoidanceBuffer, worldBounds.y + wallAvoidanceBuffer,
                worldBounds.width - wallAvoidanceBuffer, worldBounds.height - wallAvoidanceBuffer);
        this.proximity.setWorld(world);
    }

    private void applyBehavior() {
        behavior.calculateSteering(accel);
        float ang = accel.linear.angle(plane.getLinearVelocity());

        if (ang < 0) {
            plane.turn(Utils.map(ang, -15, -180, 0f, 1f));
        } else if (ang > 0) {
            plane.turn(Utils.map(ang, 15, 180, -0f, -1f));
        }
    }

    private void acquireTargetsInView() {
        final BasicAIController self = this;
        proximity.setOwner(plane);
        proximity.findNeighbors(new Proximity.ProximityCallback<Vector2>() {
            @Override
            public boolean reportNeighbor(Steerable<Vector2> neighbor) {
                if (neighbor instanceof Plane && ((Plane) neighbor).getController() instanceof PlayerController) {
                    self.setTarget(neighbor);
                    return true;
                }
                return false;
            }
        });
    }

    private enum BasicAIControllerState implements State<BasicAIController> {
        /**
         * We don't want to do anything until update is called for the first time
         * Use this init state to avoid calling any enter methods when the controller
         * is constructed
         */
        INIT() {
            @Override
            public void update(BasicAIController controller) {
                controller.stateMachine.changeState(WANDER);
            }
        },
        /**
         * Follows the player and shoots at them
         * Must acquire a target before doing this
         */
        TRACK_TARGET() {
            @Override
            public void enter(BasicAIController controller) {
                Pursue<Vector2> pursueBehavior = new Pursue<Vector2>(controller.plane, controller.target);
                pursueBehavior.setMaxPredictionTime(4);

                controller.proximity.setOwner(controller.plane);
                Separation<Vector2> separateBehavior = new Separation<Vector2>(controller.plane, controller.proximity);
                separateBehavior.setDecayCoefficient(1.5f);

                PrioritySteering<Vector2> steering = new PrioritySteering<Vector2>(controller.plane);
                steering.add(separateBehavior);
                steering.add(pursueBehavior);
                controller.behavior = steering;
            }

            @Override
            public void update(BasicAIController controller) {
                super.update(controller);

                if (controller.getTarget() != null &&
                        controller.getTarget().getPosition().dst(controller.plane.getPosition()) > controller.trackingDistance) {
                    controller.setTarget(null);
                    controller.stateMachine.changeState(WANDER);
                } else {
                    controller.applyBehavior();
//                    controller.plane.shoot();
                }
            }
        },
        /**
         * When this AI doesn't have a lock on the player, it wanders randomly
         * searching for a player to attack
         */
        WANDER() {
            @Override
            public void enter(BasicAIController controller) {
                controller.behavior = new Wander<Vector2>(controller.plane).setFaceEnabled(false)
                        .setWanderOffset(50)
                        .setWanderOrientation(controller.plane.getOrientation())
                        .setWanderRadius(250)
                        .setWanderRate(MathUtils.PI / 8);
            }

            @Override
            public void update(BasicAIController controller) {
                super.update(controller);
                controller.acquireTargetsInView();
                controller.applyBehavior();
                if (controller.getTarget() != null) {
                    controller.stateMachine.changeState(TRACK_TARGET);
                }
            }
        },
        /**
         * When at low health, the AI will run away (or something)
         */
        FLEE() {
            @Override
            public void enter(BasicAIController controller) {

            }

            @Override
            public void update(BasicAIController controller) {
                super.update(controller);
            }
        },
        /**
         * When the AI is in danger of flying out of the world
         * it'll try to seek back towards the world center.
         */
        SEEK_CENTER() {
            @Override
            public void enter(final BasicAIController controller) {
                controller.behavior = new Seek<Vector2>(controller.plane, new SteerableAdapter<Vector2>() {
                    @Override
                    public Vector2 getPosition() {
                        return controller.worldBounds.getCenter(new Vector2());
                    }
                });
            }

            @Override
            public void update(BasicAIController controller) {
                controller.applyBehavior();
                if (controller.worldBounds.contains(controller.plane.getPosition())) {
                    controller.stateMachine.revertToPreviousState();
                }
            }
        };

        @Override
        public void update(BasicAIController controller) {
            if (!controller.worldBounds.contains(controller.plane.getPosition())) {
                controller.stateMachine.changeState(SEEK_CENTER);
            }
        }

        @Override
        public void enter(BasicAIController entity) {

        }

        @Override
        public void exit(BasicAIController entity) {

        }

        @Override
        public boolean onMessage(BasicAIController entity, Telegram telegram) {
            return false;
        }

    }

}

