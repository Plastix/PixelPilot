package com.mygdx.pixelpilot.event.events.player;

import com.mygdx.pixelpilot.event.events.PlaneEvent;
import com.mygdx.pixelpilot.plane.Plane;

public abstract class PlayerEvent extends PlaneEvent {

    public PlayerEvent(Plane plane) {
        super(plane);
    }
}
