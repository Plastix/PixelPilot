package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.screen.game.hud.PlaneMarker;

public class PlaneMarkerSpawnEvent extends GameEvent {

    private PlaneMarker planeMarker;

    public PlaneMarkerSpawnEvent(PlaneMarker planeMarker) {
        this.planeMarker = planeMarker;
    }

    public PlaneMarker getPlaneMarker() {
        return planeMarker;
    }
}
