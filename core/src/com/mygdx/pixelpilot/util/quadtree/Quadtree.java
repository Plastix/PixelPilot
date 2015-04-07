package com.mygdx.pixelpilot.util.quadtree;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pixelpilot.plane.SteerableActor;


public class Quadtree extends Node<SteerableActor> {

    Leaf root;
    Array<SteerableActor> outsiders;
    ShapeRenderer renderer;

    public Quadtree(float x, float y, float w, float h) {
        super(x, y, w, h);
        this.root = new Leaf(x, y, w, h, this, new LeafPool());
        this.renderer = new ShapeRenderer();
        this.outsiders = new Array<SteerableActor>(false, 8);
    }

    @Override
    public void update() {
        // try to insert outsiders...
        for (int i = 0; i < outsiders.size; i++) {
            SteerableActor s = outsiders.get(i);
            if (s == null) continue;
            if (insert(s))
                outsiders.removeIndex(i--);
        }

        root.update();
    }

    @Override
    public boolean insert(SteerableActor t) {
        return bounds.contains(t.getX(), t.getY()) && root.insert(t);
    }

    @Override
    public int get(Rectangle box, QuadtreeCallback cb) {
        return root.get(box, cb);
    }

    @Override
    protected void insertIntoParent(SteerableActor s) {
        // the actor has gone outside of the root's bounds.
        // keep track of it until it re-enters
        outsiders.add(s);
    }

    @Override
    public void remove(SteerableActor actor) {
        if(outsiders.removeValue(actor, true)) return;
        root.remove(actor);
    }

    // debug
    public void draw(Camera cam) {
        Gdx.gl.glLineWidth(2);
        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        root.draw(renderer);
        renderer.setColor(0,0,0,1);
        for (SteerableActor outsider : outsiders) {
            renderer.circle(outsider.getX(), outsider.getY(), 50);
        }
        renderer.end();
    }

}

