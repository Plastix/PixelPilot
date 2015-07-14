package com.mygdx.pixelpilot.game.component.behavior;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.mygdx.pixelpilot.game.component.Position;


public class Attack extends Behavior {
    public Entity target;

    public Attack(Entity target) {
        setTarget(target);
    }

    @SuppressWarnings("unchecked")
    public void setTarget(Entity target) {
        if(Aspect.getAspectForOne(Position.class).isInterested(target))
            this.target = target;
        else
            throw new RuntimeException("Cannot attack a target that does not have a position component");
    }
}