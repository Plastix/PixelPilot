package com.mygdx.pixelpilot.util.quadtree;

import com.badlogic.gdx.utils.Pool;

class LeafPool extends Pool<Leaf> {
    @Override
    protected Leaf newObject() {
        return new Leaf(this);
    }
}
