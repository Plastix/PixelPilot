package com.mygdx.skystorm.event.events;

import com.mygdx.skystorm.plane.Plane;

public class PlaneDeathEvent extends PlaneEvent{
    public PlaneDeathEvent(Plane plane) {
        super(plane);
    }
}
