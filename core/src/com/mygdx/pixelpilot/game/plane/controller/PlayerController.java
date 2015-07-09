package com.mygdx.pixelpilot.game.plane.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.plane.OldPlane;
import com.mygdx.pixelpilot.game.OldWorld;
import com.mygdx.pixelpilot.util.Utils;

public class PlayerController extends Controller {

    float turnAmount = 0.5f;
    boolean accelerometer;
    private StateMachine<PlayerController> stateMachine;
    private OldPlane plane;
    protected SteeringBehavior<Vector2> behavior;
    private SteeringAcceleration<Vector2> accel = new SteeringAcceleration<Vector2>(new Vector2());
    protected Rectangle worldBounds;
    protected OldWorld world;
    private Seek<Vector2> seekBehavior;

    public void setWorld(OldWorld world) {
        this.world = world;
        worldBounds = world.getBounds();
    }

    public PlayerController() {
        this.stateMachine = new DefaultStateMachine<PlayerController>(this, PlayerControllerState.FLY);
        accelerometer = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Override
    public void control(OldPlane planeBody) {
        this.plane = planeBody;
        stateMachine.update();
    }

    private void steer(){
        if(accelerometer){
            float turn = Utils.map(Gdx.input.getAccelerometerY(), -5, 5, -1, 1);
            plane.turn(-turn);
        }

        //Desktop Controls
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            turnAmount = Math.min(turnAmount + 0.05f, 1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            turnAmount = Math.max(turnAmount - 0.05f, 0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            plane.turn(turnAmount);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            plane.turn(-turnAmount);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()){
            plane.shoot();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.V)){
            plane.hit();
        }

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

    private enum PlayerControllerState implements State<PlayerController> {
        FLY() {
            @Override
            public void enter(final PlayerController controller) {
            }
            @Override
            public void update(PlayerController controller) {
                controller.steer();
                if (!controller.worldBounds.contains(controller.plane.getPosition())) {
                    controller.stateMachine.changeState(SEEK_CENTER);
                }
            }
        },
        SEEK_CENTER() {
            @Override
            public void enter(final PlayerController controller) {
                if(controller.seekBehavior == null) {
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
            public void update(PlayerController controller) {
                controller.applyBehavior();
                if (controller.worldBounds.contains(controller.plane.getPosition())) {
                    controller.stateMachine.revertToPreviousState();
                }
            }
        };

        @Override
        public void exit(PlayerController entity) {

        }

        @Override
        public boolean onMessage(PlayerController entity, Telegram telegram) {
            return false;
        }


    }
}
