package com.mygdx.pixelpilot.plane.controller.ai;

import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.plane.SteerableActor;
import com.mygdx.pixelpilot.screen.game.World;
import com.mygdx.pixelpilot.util.quadtree.QuadtreeCallback;

public class QuadtreeProximityFinder implements Proximity<Vector2>, QuadtreeCallback {
    private World world;
    private Steerable<Vector2> owner;
    private Rectangle box;
    private ProximityCallback<Vector2> callback;

    public QuadtreeProximityFinder(float radius) {
        box = new Rectangle();
        setRadius(radius);
    }

    public void setRadius(float radius) {
        box.setSize(radius);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public Steerable<Vector2> getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Steerable<Vector2> owner) {
        this.owner = owner;
    }

    @Override
    public int findNeighbors(ProximityCallback<Vector2> callback) {
        if (world == null) throw new NullPointerException("world must be set in order to find neighbors");
        box.setCenter(owner.getPosition());
        this.callback = callback;
        return world.get(box, this);
    }

    @Override
    public void report(SteerableActor actor) {
        if (actor.getPosition().dst(owner.getPosition()) > box.width) return;
        if (actor == owner) return;
        callback.reportNeighbor(actor);
    }
}
