package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.Steerable;
import com.mygdx.pixelpilot.game.component.Velocity;

/**
 * This system exists to keep the Steerable component up to date with the other components
 * Open to suggestions on how to improve this
 */
@Wire
public class SteerableSystem extends EntityProcessingSystem {

    private ComponentMapper<Velocity> velocityComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Steerable> steerableComponentMapper;

    @SuppressWarnings("unchecked")
    public SteerableSystem() {
        super(Aspect.getAspectForAll(Steerable.class));
    }

    @Override
    protected void process(Entity e) {
        Steerable steerable = steerableComponentMapper.get(e);
        if (velocityComponentMapper.has(e)) {
            Velocity eVelocity = velocityComponentMapper.get(e);
            steerable.getLinearVelocity().set(eVelocity.getX(), eVelocity.getY());
        }
        if (positionComponentMapper.has(e)) {
            Position ePosition = positionComponentMapper.get(e);
            steerable.getPosition().set(ePosition.x, ePosition.y);
        }
    }
}
