package com.mygdx.pixelpilot.util.quadtree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pixelpilot.plane.SteerableActor;
import com.mygdx.pixelpilot.util.pools.ArrayPool;

class Leaf extends Node<SteerableActor> {
    final int CAPACITY = 2;
    final int MINSIZE = 150; // in pixels
    Array<Leaf> children = new Array<Leaf>(4);
    public static ArrayPool<SteerableActor> arrayPool = ArrayPool.steerableActorArrayPool;
    Array<SteerableActor> data = new Array<SteerableActor>(false, CAPACITY);
    Node<SteerableActor> parent;
    LeafPool pool;
    Color col = new Color();
    boolean hasChildNodes = false;

    public Leaf(LeafPool pool) {
        this(0, 0, 0, 0, null, pool);
    }

    public Leaf(float x, float y, float w, float h, Node<SteerableActor> parent, LeafPool pool) {
        super();
        this.pool = pool;
        this.set(x, y, w, h, parent);
    }

    public Leaf set(float x, float y, float w, float h, Node<SteerableActor> parent) {
        this.parent = parent;
        this.bounds.set(x, y, w, h);
        this.data.clear();
        this.col.set(MathUtils.random(0f, 1f), MathUtils.random(0f, 1f), MathUtils.random(0f, 1f), 1f);
        this.children.clear();
        this.hasChildNodes = false;
        return this;
    }

    @Override
    public boolean insert(SteerableActor t) {
        if (!bounds.contains(t.getX(), t.getY())) {
            return false;
        }

        if (!hasChildNodes) {
            // Try to insert into the data list, if there's space
            if (data.size < CAPACITY) {
                data.add(t);
                return true;
            }

            // Leaf is full. Time to split.
            if (!split()) {
                // min size reached, can't split, must add point anyway
                data.add(t);
                return true;
            }
        }

        // can assume we've split successfully
        for (Leaf child : children) {
            // Try to insert
            if (child.insert(t)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void update() {

        if (hasChildNodes) {
            for (int i = 0; i < children.size; i++) {
                if (children.get(i) == null) continue;
                children.get(i).update();
            }

            if (countDataPoints(children) == 0) {
                pool.freeAll(children);
                children.clear();
                hasChildNodes = false;
            }
        }

        for (int i = 0; i < data.size; i++) {

            SteerableActor s = data.get(i);
            if (s == null) continue;
            // if the point's not in the quad any more, it's time to pass it up the tree to the parent
            // if it's not in the parent, maybe it's in the parent's parent.
            if (!bounds.contains(s.getX(), s.getY())) {
                insertIntoParent(s);
                data.removeIndex(i--);
            }
        }
    }

    @Override
    protected void insertIntoParent(SteerableActor s) {
        if (parent.insert(s)) return;
        parent.insertIntoParent(s);
    }

    /**
     * Gets all the actors inside the given rectangle
     * @param box The rectangle specifying which actors in the tree should be selected
     * @param cb The callback method to call for each actor
     * @return The number of actors found
     */
    @Override
    public int get(Rectangle box, QuadtreeCallback cb) {
        int count = 0;
        if (!box.overlaps(bounds)) {
            return count;
        }

        // todo: remove this alloc...
        Array<SteerableActor> found = withinBox(data, box);
        for (SteerableActor t : found) {
            cb.report(t);
            count++;
        }
        arrayPool.free(found);

        if (!hasChildNodes)
            return count;

        for (Leaf c : children) {
            count += c.get(box, cb);
        }

        return count;
    }

    @Override
    public void remove(SteerableActor actor) {
        if(!data.removeValue(actor, true)){
            for(Leaf child : children)
                child.remove(actor);
        }
    }

    // todo: remove array
    private Array<SteerableActor> getAllData() {
        Array<SteerableActor> allData = arrayPool.obtain();
        for (Leaf child : children) {
            if (child.hasChildNodes) {
                Array<SteerableActor> childData = child.getAllData();
                allData.addAll(childData);
                arrayPool.free(childData);
            } else {
                allData.addAll(child.data);
            }
        }
        return allData;
    }

    private Array<SteerableActor> withinBox(Array<SteerableActor> arr, Rectangle box) {
        Array<SteerableActor> objectsInRadius = arrayPool.obtain();
        for (SteerableActor t : arr) {
            if (box.contains(t.getX(), t.getY())) {
                objectsInRadius.add(t);
            }
        }
        return objectsInRadius;
    }

    private int countDataPoints(Array<Leaf> nodes) {
        Array<SteerableActor> allDat = getAllData();
        int size = allDat.size;
        arrayPool.free(allDat);
        return size;
    }

    private boolean split() {
        if (bounds.width / 2 < MINSIZE)
            return false;
        children.clear();
        float x = bounds.x;
        float y = bounds.y;
        float w = bounds.width;
        float h = bounds.height;
        children.add(pool.obtain().set(x, y, w / 2, h / 2, this));
        children.add(pool.obtain().set(x + w / 2, y, w / 2, h / 2, this));
        children.add(pool.obtain().set(x, y + h / 2, w / 2, h / 2, this));
        children.add(pool.obtain().set(x + w / 2, y + h / 2, w / 2, h / 2, this));
        hasChildNodes = true;
        distributeChildren();
        return true;
    }

    private void distributeChildren() {
        for (int i = 0; i < data.size; i++) {
            SteerableActor c = data.get(i);
            for (Leaf n : children) {
                if (n.bounds.contains(c.getX(), c.getY())) {
                    n.insert(c);
                    data.removeIndex(i--);
                    break;
                }
            }
        }
    }

    // debug
    public void draw(ShapeRenderer r) {
        r.setColor(col);
        r.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        for (Leaf c : children)
            if (c != null)
                c.draw(r);
        for (SteerableActor a : data) {
            if (a != null) {
                r.circle(a.getX(), a.getY(), 50);
            }
        }
    }
}
