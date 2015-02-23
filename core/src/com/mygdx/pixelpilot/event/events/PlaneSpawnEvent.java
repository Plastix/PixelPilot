package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.plane.Plane;

public class PlaneSpawnEvent extends PlaneEvent{
    public PlaneSpawnEvent(Plane plane) {
        super(plane);
    }
}
