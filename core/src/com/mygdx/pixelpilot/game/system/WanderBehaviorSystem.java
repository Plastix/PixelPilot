package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.Rotation;
import com.mygdx.pixelpilot.game.component.Turning;
import com.mygdx.pixelpilot.game.component.Velocity;
import com.mygdx.pixelpilot.game.component.behavior.WanderBehavior;
import com.mygdx.pixelpilot.util.Utils;

@Wire
public class WanderBehaviorSystem extends BehaviorProcessingSystem {
    private ComponentMapper<Velocity> velocity;
    private ComponentMapper<Turning> turning;
    private ComponentMapper<WanderBehavior> wander;

    @SuppressWarnings("unchecked")
    public WanderBehaviorSystem() {
        super(Aspect.getAspectForAll(
                WanderBehavior.class,
                Position.class,
                Velocity.class,
                Turning.class,
                Rotation.class
        ));
    }

    @Override
    protected void process(Entity entity) {
        WanderBehavior wander = this.wander.get(entity);
        Turning turning = this.turning.get(entity);
        Velocity velocity = this.velocity.get(entity);
        applyBehavior(wander.wander, velocity, turning);
    }
}
