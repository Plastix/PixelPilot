package com.mygdx.pixelpilot.util.quadtree;

import com.mygdx.pixelpilot.game.plane.SteerableActor;

public interface QuadtreeCallback {
    void report(SteerableActor t);
}
