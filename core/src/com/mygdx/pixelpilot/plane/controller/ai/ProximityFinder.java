package com.mygdx.pixelpilot.plane.controller.ai;

import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pixelpilot.plane.SteerableActor;
import com.mygdx.pixelpilot.screen.game.World;

public class ProximityFinder implements Proximity<Vector2> {
    private World world;
    private Steerable<Vector2> owner;
    private Rectangle box;

    public ProximityFinder(float radius) {
        box = new Rectangle();
        setRadius(radius);
    }

    public float getRadius() {
        return box.width;
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
        Array<SteerableActor> neighbors = world.get(box);
        if (neighbors == null) return 0;
        int size = 0;
        for (SteerableActor neighbor : neighbors) {
            if (neighbor.getPosition().dst(owner.getPosition()) > box.width) continue;
            if (neighbor == owner) continue;
            callback.reportNeighbor(neighbor);
            size++;
        }
        return size;
    }
}