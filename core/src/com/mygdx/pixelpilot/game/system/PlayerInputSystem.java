package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.pixelpilot.game.component.Player;
import com.mygdx.pixelpilot.game.component.Turning;
import com.mygdx.pixelpilot.util.Utils;

@Wire
public class PlayerInputSystem extends EntityProcessingSystem {

    private ComponentMapper<Turning> turning;
    private float turnAmount = 0.5f;
    private boolean hasAccelerometer;

    @SuppressWarnings("unchecked")
    public PlayerInputSystem() {
        super(Aspect.getAspectForAll(
                Player.class,
                Turning.class
        ));
        hasAccelerometer = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Override
    protected void process(Entity e) {
        Turning turning = this.turning.get(e);

        if (hasAccelerometer) {
            float maxAccelerometerValue = 5;
            float maxTurnValue = 1;
            float turnAmount = Utils.map(Gdx.input.getAccelerometerY(), // map the accelerometer input
                    -maxAccelerometerValue, maxAccelerometerValue, // from ± 5 (half the full value of ± 10, because we only care up to 180°)
                    -maxTurnValue, maxTurnValue); // to ± 1 (which is what the turning system accepts)
            turning.turn(-turnAmount); // then turn the entity
        }

        // Desktop Controls
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            turnAmount = Math.min(turnAmount + 0.05f, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            turnAmount = Math.max(turnAmount - 0.05f, 0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            turning.turn(turnAmount);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            turning.turn(-turnAmount);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
//            plane.shoot();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.V)) {
//            plane.hit();
        }

    }
}
