package com.mygdx.pixelpilot.event.events.ai;

import com.mygdx.pixelpilot.event.events.PlaneEvent;
import com.mygdx.pixelpilot.plane.Plane;

public class AISpawnEvent extends PlaneEvent {
    public AISpawnEvent(Plane plane) {
        super(plane);
    }
}
