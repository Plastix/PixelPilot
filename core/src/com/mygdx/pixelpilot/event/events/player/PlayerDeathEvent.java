package com.mygdx.pixelpilot.event.events.player;

import com.mygdx.pixelpilot.game.plane.Plane;

public class PlayerDeathEvent extends PlayerEvent {

    public PlayerDeathEvent(Plane plane) {
        super(plane);
    }
}
