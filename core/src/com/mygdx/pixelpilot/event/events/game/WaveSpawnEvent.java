package com.mygdx.pixelpilot.event.events.game;

import com.mygdx.pixelpilot.data.level.Wave;
import com.mygdx.pixelpilot.event.events.GameEvent;

public class WaveSpawnEvent extends GameEvent {

    private Wave wave;

    public WaveSpawnEvent(Wave wave) {
        this.wave = wave;
    }

    public Wave getWave() {
        return wave;
    }
}
