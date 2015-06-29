package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.game.hud.Marker;

public class MarkerSpawnEvent extends GameEvent {

    private Marker marker;

    public MarkerSpawnEvent(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }
}
