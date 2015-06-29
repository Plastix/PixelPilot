package com.mygdx.pixelpilot.game.plane.controller;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.OldWorld;

public abstract class AIController extends Controller {
    protected Steerable<Vector2> target;
    protected OldWorld world;
    protected SteeringBehavior<Vector2> behavior;
    protected Rectangle worldBounds;

    public OldWorld getWorld() {
        return world;
    }

    public void setWorld(OldWorld world) {
        this.world = world;
        worldBounds = world.getBounds();
    }

    public void setTarget(Steerable<Vector2> target) {
        this.target = target;
    }

    public Steerable<Vector2> getTarget() {
        return target;
    }

}
