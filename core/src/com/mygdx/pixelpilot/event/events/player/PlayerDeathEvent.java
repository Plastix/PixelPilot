package com.mygdx.pixelpilot.event.events.player;

import com.mygdx.pixelpilot.plane.Plane;

public class PlayerDeathEvent extends PlayerEvent {

    public PlayerDeathEvent(Plane plane) {
        super(plane);
    }
}
