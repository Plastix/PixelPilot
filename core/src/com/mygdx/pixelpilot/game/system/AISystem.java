package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mygdx.pixelpilot.game.component.AI;
import com.mygdx.pixelpilot.game.component.Eyes;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.behavior.Behavior;

@Wire
public class AISystem extends EntityProcessingSystem {

    ComponentMapper<Position> position;
    ComponentMapper<Behavior> behavior;

    @SuppressWarnings("unchecked")
    public AISystem() {
        super(Aspect.getAspectForAll(
                AI.class,
                Position.class,
                Behavior.class,
                Eyes.class
        ));
    }

    @Override
    protected void process(Entity e) {
        Position position = this.position.get(e);
        Behavior behavior = this.behavior.get(e);
    }
}
