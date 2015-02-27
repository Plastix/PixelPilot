package com.mygdx.pixelpilot.plane.controller;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.pixelpilot.screen.game.World;

public abstract class AIController extends Controller {
    protected Steerable<Vector2> target;
    protected World world;
    protected SteeringBehavior<Vector2> behavior;
    protected Rectangle worldBounds;

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
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
