package com.mygdx.skystorm.event.events;

import com.mygdx.skystorm.plane.Plane;

public class PlaneSpawnEvent extends PlaneEvent{
    public PlaneSpawnEvent(Plane plane) {
        super(plane);
    }
}
