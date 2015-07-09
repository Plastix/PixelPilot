package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.pixelpilot.game.component.*;
import com.mygdx.pixelpilot.util.Utils;

@Wire
public class PlayerInputSystem extends EntityProcessingSystem {

    private ComponentMapper<Rotation> rotation;
    private ComponentMapper<Velocity> velocity;
    private ComponentMapper<PlaneDefinition> planeDefs;
    private float turnAmount = 0.5f;
    private boolean hasAccelerometer;

    @SuppressWarnings("unchecked")
    public PlayerInputSystem() {
        super(Aspect.getAspectForAll(
                Player.class,
                Velocity.class,
                Rotation.class,
                PlaneDefinition.class
        ));
        hasAccelerometer = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Override
    protected void process(Entity e) {
        Rotation r = rotation.get(e);
        Velocity v = velocity.get(e);
        PlaneDefinition def = planeDefs.get(e);

        if (hasAccelerometer) {
            float turn = Utils.map(Gdx.input.getAccelerometerY(), -5, 5, -1, 1);
            turn(v, r, -turn, def);
        }

        //Desktop Controls
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            turnAmount = Math.min(turnAmount + 0.05f, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            turnAmount = Math.max(turnAmount - 0.05f, 0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            turn(v, r, turnAmount, def);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            turn(v, r, -turnAmount, def);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
//            plane.shoot();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.V)) {
//            plane.hit();
        }

    }

    /**
     * Causes the plane to change its heading
     * @param turnAmount amount to turn this frame, expects to be in range [-1, 1]
     */
    public void turn(Velocity velocity, Rotation rotation, float turnAmount, PlaneDefinition def) {

        // based on http://aviation.stackexchange.com/a/2872
        float minTurnRadiusAng = MathUtils.radDeg * velocity.vector.len() / def.minTurnRadius;

        float turnAng = Utils.map(turnAmount, -1f, 1f, -minTurnRadiusAng, minTurnRadiusAng);
        velocity.vector.rotate(turnAng);
        rotation.set(velocity.vector.angle() - 90);
    }

}
