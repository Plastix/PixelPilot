package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.game.plane.OldPlane;

public abstract class PlaneEvent extends GameEvent {
    private OldPlane plane;
    public PlaneEvent(OldPlane plane) {
        this.plane = plane;
    }

    public OldPlane getPlane(){
        return plane;
    }

}
