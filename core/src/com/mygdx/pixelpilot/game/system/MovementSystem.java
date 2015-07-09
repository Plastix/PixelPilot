package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.Velocity;

@Wire
public class MovementSystem extends EntityProcessingSystem {
    private ComponentMapper<Position> position;
    private ComponentMapper<Velocity> velocity;

    @SuppressWarnings("unchecked")
    public MovementSystem() {
        super(
                Aspect.getAspectForAll(
                        Position.class,
                        Velocity.class
                ));
    }

    @Override
    protected void process(Entity e) {
        Velocity velocity = this.velocity.get(e);
        Position position = this.position.get(e);
        position.add(velocity.getX(), velocity.getY());
    }
}
