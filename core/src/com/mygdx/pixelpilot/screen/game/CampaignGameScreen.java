package com.mygdx.pixelpilot.screen.game;

import com.mygdx.pixelpilot.PixelPilot;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.level.Level;
import com.mygdx.pixelpilot.data.level.Wave;
import com.mygdx.pixelpilot.event.*;
import com.mygdx.pixelpilot.event.events.PlaneSpawnEvent;
import com.mygdx.pixelpilot.event.events.game.WaveSpawnEvent;
import com.mygdx.pixelpilot.plane.*;
import com.mygdx.pixelpilot.screen.game.hud.HUD;

import java.util.List;

public class CampaignGameScreen extends GameScreen implements Listener {

    private World world;
    private HUD hud;
    private int currentLevel;
    private int currentWave;

    public CampaignGameScreen(PixelPilot game) {
        super(game);
        hud = new HUD();
        world = new World(3000, 3000);
        currentLevel = 0;
        currentWave = 0;

        addStage(world);
        addStage(hud);

        spawnPlayer();
        spawnWave();

    }

    private void spawnWave(){
        Level level = GameData.levels.get(currentLevel);
        List<Wave> waves = level.waves;
        Wave wave = waves.get(currentWave);
        Events.emit(new WaveSpawnEvent(wave), this);

        for(PlanePreset preset : wave.enemies){
            Plane plane = PlaneFactory.build(preset);
            Events.emit(new PlaneSpawnEvent(plane), this);
        }

        currentWave++;
    }

}
