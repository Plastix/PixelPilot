package com.mygdx.pixelpilot.event.events.player;

import com.mygdx.pixelpilot.game.plane.OldPlane;

public class PlayerSpawnEvent extends PlayerEvent {
    public PlayerSpawnEvent(OldPlane plane) {
        super(plane);
    }
}
