package com.mygdx.pixelpilot.event.events.ai;

import com.mygdx.pixelpilot.event.events.PlaneEvent;
import com.mygdx.pixelpilot.game.plane.OldPlane;

public class AISpawnEvent extends PlaneEvent {
    public AISpawnEvent(OldPlane plane) {
        super(plane);
    }
}
