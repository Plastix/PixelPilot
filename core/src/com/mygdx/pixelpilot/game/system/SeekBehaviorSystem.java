package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.component.*;
import com.mygdx.pixelpilot.game.component.behavior.SeekBehavior;

// todo: Where to put state change logic? In behavior systems?
@Wire
public class SeekBehaviorSystem extends BehaviorProcessingSystem {
    private ComponentMapper<Velocity> velocity;
    private ComponentMapper<Turning> turning;
    private ComponentMapper<SeekBehavior> seek;
    private ComponentMapper<Rotation> rotation;
    private ComponentMapper<Position> position;
    private ComponentMapper<Steerable> steerable;
    private ComponentMapper<Tolerance> tolerance;

    private Seek<Vector2> behavior;

    @SuppressWarnings("unchecked")
    public SeekBehaviorSystem() {
        super(Aspect.getAspectForAll(
                SeekBehavior.class,
                Position.class,
                Velocity.class,
                Turning.class,
                Rotation.class,
                Steerable.class,
                Target.class
        ));
    }

    @Override
    protected void initialize() {
        behavior = new Seek<Vector2>(new SteerableAdapter<Vector2>(), new SteerableAdapter<Vector2>());
    }

    @Override
    protected void process(final Entity e) {
        behavior.setOwner(steerable.get(e));
        behavior.setTarget(seek.get(e).target);
        if (!tolerance.has(e))
            applyBehavior(behavior, velocity.get(e), turning.get(e));
        else
            applyBehavior(behavior, velocity.get(e), turning.get(e), tolerance.get(e).min, tolerance.get(e).max);
    }
}
