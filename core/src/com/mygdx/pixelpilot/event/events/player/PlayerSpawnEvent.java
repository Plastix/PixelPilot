package com.mygdx.pixelpilot.event.events.player;

import com.mygdx.pixelpilot.game.plane.Plane;

public class PlayerSpawnEvent extends PlayerEvent {
    public PlayerSpawnEvent(Plane plane) {
        super(plane);
    }
}
