package com.mygdx.pixelpilot.event.events.ai;

import com.mygdx.pixelpilot.event.events.PlaneEvent;
import com.mygdx.pixelpilot.game.plane.Plane;

public class AIDeathEvent extends PlaneEvent {
    public AIDeathEvent(Plane plane) {
        super(plane);
    }
}
