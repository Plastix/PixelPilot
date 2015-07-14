package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.Rotation;
import com.mygdx.pixelpilot.game.component.Turning;
import com.mygdx.pixelpilot.game.component.Velocity;
import com.mygdx.pixelpilot.game.component.behavior.Wander;
import com.mygdx.pixelpilot.util.Utils;

public class WanderBehaviorSystem extends EntityProcessingSystem {
    ComponentMapper<Velocity> velocity;
    ComponentMapper<Turning> turning;

    ComponentMapper<Wander> wander;
    SteeringAcceleration<Vector2> acceleration;

    @SuppressWarnings("unchecked")
    public WanderBehaviorSystem() {
        super(Aspect.getAspectForAll(
                Wander.class,
                Position.class,
                Velocity.class,
                Turning.class,
                Rotation.class
        ));
    }

    @Override
    protected void process(Entity entity) {
        Wander w = this.wander.get(entity);
        Turning t = this.turning.get(entity);
        Velocity v = this.velocity.get(entity);
        w.wander.calculateSteering(acceleration);
        float ang = acceleration.linear.angle(v.vector);
        if (ang < 0) {
            t.turn(Utils.map(ang, -15, -180, 0f, 1f));
        } else if (ang > 0) {
            t.turn(Utils.map(ang, 15, 180, -0f, -1f));
        }
    }
}
