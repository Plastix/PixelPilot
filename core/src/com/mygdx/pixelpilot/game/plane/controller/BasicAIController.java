package com.mygdx.pixelpilot.game.plane.controller;

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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.ai.AIDeathEvent;
import com.mygdx.pixelpilot.game.plane.OldPlane;
import com.mygdx.pixelpilot.game.plane.controller.ai.PlayerProximityCallback;
import com.mygdx.pixelpilot.game.plane.controller.ai.QuadtreeProximityFinder;
import com.mygdx.pixelpilot.game.OldWorld;
import com.mygdx.pixelpilot.util.Utils;

// TODO: this class is due for a major refactor
public class BasicAIController extends AIController {

    private final float detectionDistance = 500;
    private final float trackingDistance = 1500;
    private OldPlane plane;
    private QuadtreeProximityFinder proximity;
    private Proximity.ProximityCallback<Vector2> targetProximityCallback;
    private SteeringAcceleration<Vector2> accel = new SteeringAcceleration<Vector2>(new Vector2());
    private StateMachine<BasicAIController> stateMachine;
    private PrioritySteering<Vector2> trackingBehavior;
    private Wander<Vector2> wanderBehavior;
    private Seek<Vector2> seekBehavior;
    private int spiralDirection;

    public BasicAIController() {
        this.stateMachine = new DefaultStateMachine<BasicAIController>(this, BasicAIControllerState.INIT);
        this.proximity = new QuadtreeProximityFinder(detectionDistance);
        this.targetProximityCallback = new PlayerProximityCallback(this);
    }

    @Override
    public void control(OldPlane planeBody) {
        this.plane = planeBody;
        stateMachine.update();
    }

    @Override
    public void setWorld(OldWorld world) {
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
        proximity.setOwner(plane);
        proximity.findNeighbors(targetProximityCallback);
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
                if(controller.trackingBehavior == null) {
                    controller.trackingBehavior = new PrioritySteering<Vector2>(controller.plane);
                    Separation<Vector2> separateBehavior = new Separation<Vector2>(controller.plane, controller.proximity);
                    separateBehavior.setDecayCoefficient(10.5f);
                    Pursue<Vector2> pursueBehavior = new Pursue<Vector2>(controller.plane, controller.target);
                    pursueBehavior.setMaxPredictionTime(4);
                    controller.trackingBehavior.add(separateBehavior);
                    controller.trackingBehavior.add(pursueBehavior);

                }
                controller.proximity.setOwner(controller.plane);
                controller.behavior = controller.trackingBehavior;
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
                    OldPlane plane = controller.plane;
                    Steerable<Vector2> target = controller.getTarget();
                    float x = plane.getX() - target.getPosition().x;
                    float y = plane.getY() - target.getPosition().y;
                    float angle = MathUtils.atan2(y, x) * MathUtils.radDeg;
                    angle = (angle - plane.getRotation()) + 90;
                    if(Math.abs(angle) < 45) { // if in view then fire
                        plane.shootIfInRange(target);
                    }
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
                if(controller.wanderBehavior == null){
                   controller.wanderBehavior = new Wander<Vector2>(controller.plane).setFaceEnabled(false)
                            .setWanderOffset(50)
                            .setWanderOrientation(controller.plane.getOrientation())
                            .setWanderRadius(250)
                            .setWanderRate(MathUtils.PI / 8);
                }
                controller.behavior = controller.wanderBehavior;
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
                if(controller.seekBehavior == null){
                    controller.seekBehavior = new Seek<Vector2>(controller.plane, new SteerableAdapter<Vector2>() {
                        Vector2 temp = new Vector2();
                        @Override
                        public Vector2 getPosition() {
                            return controller.worldBounds.getCenter(temp);
                        }
                    });
                }
                controller.behavior = controller.seekBehavior;
            }

            @Override
            public void update(BasicAIController controller) {
                controller.applyBehavior();
                if (controller.worldBounds.contains(controller.plane.getPosition())) {
                    controller.stateMachine.revertToPreviousState();
                }
            }
        },
        /**
         * When the Plane dies animate the death.
         * Plane spirals and shrinks in size
         * TODO: Better animation! (and particles)
         */
        DEATH_SPIRAL(){
            @Override
            public void enter(final BasicAIController controller) {
                super.enter(controller);

                if(Math.random() < 0.5f){
                    controller.spiralDirection = 1;
                }else{
                    controller.spiralDirection = -1;
                }

                controller.plane.addAction(

                        Actions.sequence(
                                Actions.scaleTo(0, 0, 2),
                                Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        Events.getBus().publish(new AIDeathEvent(controller.plane));
                                    }
                                })
                        ));

            }

            @Override
            public void update(BasicAIController controller) {
                super.update(controller);
                controller.plane.turn(controller.spiralDirection);
            }

        };

        @Override
        public void update(BasicAIController controller) {
            if(!controller.stateMachine.isInState(DEATH_SPIRAL)) {
                if (!controller.worldBounds.contains(controller.plane.getPosition())) {
                    controller.stateMachine.changeState(SEEK_CENTER);
                }
                if (controller.plane.isDead()) {
                    controller.stateMachine.changeState(DEATH_SPIRAL);
                }
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

