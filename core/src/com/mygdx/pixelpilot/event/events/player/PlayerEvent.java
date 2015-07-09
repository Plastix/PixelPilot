package com.mygdx.pixelpilot.event.events.player;

import com.mygdx.pixelpilot.event.events.PlaneEvent;
import com.mygdx.pixelpilot.game.plane.OldPlane;

public abstract class PlayerEvent extends PlaneEvent {

    public PlayerEvent(OldPlane plane) {
        super(plane);
    }
}
