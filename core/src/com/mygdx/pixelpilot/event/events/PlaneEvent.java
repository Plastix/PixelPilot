package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.game.plane.Plane;

public abstract class PlaneEvent extends GameEvent {
    private Plane plane;
    public PlaneEvent(Plane plane) {
        this.plane = plane;
    }

    public Plane getPlane(){
        return plane;
    }

}
