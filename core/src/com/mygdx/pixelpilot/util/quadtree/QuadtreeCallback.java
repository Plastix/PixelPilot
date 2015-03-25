package com.mygdx.pixelpilot.util.quadtree;

import com.mygdx.pixelpilot.plane.SteerableActor;

public interface QuadtreeCallback {
    void report(SteerableActor t);
}
