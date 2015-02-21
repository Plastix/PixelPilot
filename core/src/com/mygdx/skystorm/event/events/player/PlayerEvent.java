package com.mygdx.skystorm.event.events.player;

import com.mygdx.skystorm.event.events.PlaneEvent;
import com.mygdx.skystorm.plane.Plane;

public abstract class PlayerEvent extends PlaneEvent {

    public PlayerEvent(Plane plane) {
        super(plane);
    }
}
