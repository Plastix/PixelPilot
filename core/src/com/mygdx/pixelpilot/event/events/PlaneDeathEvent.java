package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.plane.Plane;

public class PlaneDeathEvent extends PlaneEvent{
    public PlaneDeathEvent(Plane plane) {
        super(plane);
    }
}
