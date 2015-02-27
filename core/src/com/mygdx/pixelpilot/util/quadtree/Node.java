package com.mygdx.pixelpilot.util.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

abstract class Node<T> {
    Rectangle bounds;

    public Node() {
        bounds = new Rectangle();
    }

    public Node(float x, float y, float w, float h) {
        this();
        bounds.set(x, y, w, h);
    }

    public abstract boolean insert(T t);

    public abstract void update();

    protected abstract void insertIntoParent(T s);

    public abstract Array<T> get(Rectangle box);

}
