package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.component.Turning;
import com.mygdx.pixelpilot.game.component.Velocity;
import com.mygdx.pixelpilot.util.Utils;

public abstract class BehaviorProcessingSystem extends EntityProcessingSystem {
    protected SteeringAcceleration<Vector2> acceleration;

    public BehaviorProcessingSystem(Aspect aspect) {
        super(aspect);
        acceleration = new SteeringAcceleration<Vector2>(new Vector2());
    }

    void applyBehavior(SteeringBehavior<Vector2> behavior, Velocity v, Turning t) {
        behavior.calculateSteering(acceleration);
        float ang = acceleration.linear.angle(v.vector);
        if (ang < 0) {
            t.turn(Utils.map(ang, -15, -180, 0f, 1f));
        } else if (ang > 0) {
            t.turn(Utils.map(ang, 15, 180, -0f, -1f));
        }
        acceleration.setZero();
    }
}
