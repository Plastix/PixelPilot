package com.mygdx.skystorm.event.events.player;

import com.mygdx.skystorm.plane.Plane;

public class PlayerDeathEvent extends PlayerEvent {

    public PlayerDeathEvent(Plane plane) {
        super(plane);
    }
}
