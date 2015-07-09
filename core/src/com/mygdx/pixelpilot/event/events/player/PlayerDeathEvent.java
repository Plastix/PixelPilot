package com.mygdx.pixelpilot.event.events.player;

import com.mygdx.pixelpilot.game.plane.OldPlane;

public class PlayerDeathEvent extends PlayerEvent {

    public PlayerDeathEvent(OldPlane plane) {
        super(plane);
    }
}
