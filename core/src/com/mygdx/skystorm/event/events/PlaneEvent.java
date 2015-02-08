package com.mygdx.skystorm.event.events;

import com.mygdx.skystorm.plane.Plane;

public abstract class PlaneEvent extends GameEvent {
    private Plane plane;
    public PlaneEvent(Plane plane) {
        this.plane = plane;
    }

    public Plane getPlane(){
        return plane;
    }

}
