package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.pixelpilot.game.component.Rotation;
import com.mygdx.pixelpilot.game.component.Turning;
import com.mygdx.pixelpilot.game.component.Velocity;
import com.mygdx.pixelpilot.util.Utils;

@Wire
public class TurningSystem extends EntityProcessingSystem {
    private ComponentMapper<Rotation> rotation;
    private ComponentMapper<Velocity> velocity;
    private ComponentMapper<Turning> turning;

    @SuppressWarnings("unchecked")
    public TurningSystem() {
        super(Aspect.getAspectForAll(
                Rotation.class,
                Velocity.class,
                Turning.class
        ));
    }

    @Override
    protected void process(Entity entity) {

        Velocity velocity = this.velocity.get(entity);
        Turning turning = this.turning.get(entity);
        Rotation rotation = this.rotation.get(entity);

        // based on http://aviation.stackexchange.com/a/2872
        float minTurnRadiusAng = MathUtils.radDeg * velocity.vector.len() / turning.minTurnRadius;

        float turnAng = Utils.map(turning.turnAmount, -1f, 1f, -minTurnRadiusAng, minTurnRadiusAng);
        velocity.vector.rotate(turnAng);
        rotation.set(velocity.vector.angle() - 90);
        turning.reset();
    }

}
