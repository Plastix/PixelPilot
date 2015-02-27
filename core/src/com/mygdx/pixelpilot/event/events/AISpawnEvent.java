package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.plane.Plane;

public class AISpawnEvent extends PlaneEvent{
    public AISpawnEvent(Plane plane) {
        super(plane);
    }
}
