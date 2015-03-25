package com.mygdx.pixelpilot.screen.game;

import com.mygdx.pixelpilot.PixelPilot;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.level.Level;
import com.mygdx.pixelpilot.data.level.Wave;
import com.mygdx.pixelpilot.event.*;
import com.mygdx.pixelpilot.event.events.game.WaveSpawnEvent;
import com.mygdx.pixelpilot.event.events.ai.AISpawnEvent;
import com.mygdx.pixelpilot.plane.*;
import com.mygdx.pixelpilot.plane.controller.AIController;
import com.mygdx.pixelpilot.screen.game.hud.HUD;

import java.util.List;

public class CampaignGameScreen extends GameScreen implements Listener {

    private World world;
    private HUD hud;
    private int currentLevel;
    private int currentWave;

    public CampaignGameScreen(PixelPilot game) {
        super(game);

        Events.register(this);

        hud = new HUD();
        world = new World(3000, 3000);
        currentLevel = 0;
        currentWave = 0;

        addStage(world);
        addStage(hud);

        spawnPlayer();

//        Plane plane = PlaneFactory.build(GameData.planePresets.get(0));
//        Events.emit(new AISpawnEvent(plane), this);

        spawnWave();

    }

    private void spawnWave() {
        Level level = GameData.levels.get(currentLevel);
        List<Wave> waves = level.waves;
        Wave wave = waves.get(currentWave);
        Events.emit(new WaveSpawnEvent(wave), this);

        for(PlanePreset preset : wave.enemies) {
            Plane plane = PlaneFactory.build(preset);
            Events.emit(new AISpawnEvent(plane), this);
        }

        currentWave++;
    }

    /**
     * Spawns a wave with a given number of AI planes
     * Intended for debug use only
     * @param numberOfPlanes the number of planes to spawn
     */
    private void spawnWave(int numberOfPlanes) {
        Level level = GameData.levels.get(currentLevel);
        List<Wave> waves = level.waves;
        Wave wave = waves.get(currentWave);
        Events.emit(new WaveSpawnEvent(wave), this);
        PlanePreset preset = wave.enemies.get(0);
        for (int i = 0; i < numberOfPlanes; i++) {
            Plane plane = PlaneFactory.build(preset);
            Events.emit(new AISpawnEvent(plane), this);
        }
    }



    @EventHandler
    public void handleEnemyPlaneSpawn(AISpawnEvent event){
        ((AIController)event.getPlane().getController()).setWorld(world);
    }
}
