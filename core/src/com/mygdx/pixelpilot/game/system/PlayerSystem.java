package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.StageConfig;
import com.mygdx.pixelpilot.game.component.Player;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.Turning;
import com.mygdx.pixelpilot.game.component.behavior.SeekBehavior;
import com.mygdx.pixelpilot.util.Utils;

@Wire
public class PlayerSystem extends EntityProcessingSystem {

    @Wire
    private StageConfig config;

    private ComponentMapper<Turning> turningMapper;
    private ComponentMapper<Player> playerMapper;
    private ComponentMapper<Position> positionMapper;
    private ComponentMapper<SeekBehavior> seekBehaviorMapper;

    private float turnAmount = 0.5f;
    private boolean hasAccelerometer;

    @SuppressWarnings("unchecked")
    public PlayerSystem() {
        super(Aspect.getAspectForAll(
                Player.class,
                Turning.class
        ));
        hasAccelerometer = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

    }

    @Override
    protected void process(Entity e) {
        if (playerMapper.get(e).controllable) {
            steer(e);
        }

        // if we're out of bounds seek center
        Position position = this.positionMapper.get(e);
        if (!config.worldBounds.contains(position.x, position.y)) {
            playerMapper.get(e).controllable = false;
            if (!seekBehaviorMapper.has(e)) {
                e.edit().add(new SeekBehavior(new Vector2(
                        config.worldBounds.width / 2 + config.worldBounds.x,
                        config.worldBounds.height / 2 + config.worldBounds.y)));
            }
        } else {
            if (seekBehaviorMapper.has(e)) {
                e.edit().remove(SeekBehavior.class);
            }
            playerMapper.get(e).controllable = true;
        }
    }

    private void steer(Entity e) {
        Turning turning = this.turningMapper.get(e);

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
