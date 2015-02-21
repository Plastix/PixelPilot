package com.mygdx.skystorm.event.events.player;

import com.mygdx.skystorm.plane.Plane;

public class PlayerSpawnEvent extends PlayerEvent {
    public PlayerSpawnEvent(Plane plane) {
        super(plane);
    }
}
