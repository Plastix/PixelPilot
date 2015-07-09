package com.mygdx.pixelpilot.event.events.ai;

import com.mygdx.pixelpilot.event.events.PlaneEvent;
import com.mygdx.pixelpilot.game.plane.OldPlane;

public class AIDeathEvent extends PlaneEvent {
    public AIDeathEvent(OldPlane plane) {
        super(plane);
    }
}
