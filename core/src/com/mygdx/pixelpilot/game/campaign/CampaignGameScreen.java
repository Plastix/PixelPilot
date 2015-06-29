package com.mygdx.pixelpilot.game.campaign;

import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.assetpack.*;
import com.mygdx.pixelpilot.data.level.Level;
import com.mygdx.pixelpilot.data.level.Wave;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.ai.AISpawnEvent;
import com.mygdx.pixelpilot.event.events.game.WaveSpawnEvent;
import com.mygdx.pixelpilot.game.plane.Plane;
import com.mygdx.pixelpilot.game.plane.PlaneFactory;
import com.mygdx.pixelpilot.game.plane.PlanePreset;
import com.mygdx.pixelpilot.game.plane.controller.AIController;
import com.mygdx.pixelpilot.game.GameScreen;
import com.mygdx.pixelpilot.DependentBuilder;
import net.engio.mbassy.listener.Handler;

import java.util.List;

public class CampaignGameScreen extends GameScreen {

    private int currentLevel;
    private int currentWave;

    private CampaignGameScreen() {
        currentLevel = 0;
        currentWave = 0;
        setHUD(new CampaignHUD());
        spawnPlayer();
        spawnWave(1);
    }

    public static class Loader extends DependentBuilder<CampaignGameScreen> {
        public Loader() {
            super(
                    new CommonAssets(),
                    new GameAssets(),
                    new PauseMenuAssets(),
                    new HUDAssets(),
                    new OptionsMenuAssets()
            );
        }

        public CampaignGameScreen build() {
            return new CampaignGameScreen();
        }

    }


    private void spawnWave() {
        Level level = GameData.levels.get(currentLevel);
        List<Wave> waves = level.waves;
        Wave wave = waves.get(currentWave);
        Events.getBus().publish(new WaveSpawnEvent(wave));

        for (PlanePreset preset : wave.enemies) {
            Plane plane = PlaneFactory.build(preset);
            Events.getBus().publish(new AISpawnEvent(plane));
        }

        currentWave++;
    }

    /**
     * Spawns a wave with a given number of AI planes
     * Intended for debug use only
     *
     * @param numberOfPlanes the number of planes to spawn
     */
    private void spawnWave(int numberOfPlanes) {
        Level level = GameData.levels.get(currentLevel);
        List<Wave> waves = level.waves;
        Wave wave = waves.get(currentWave);
        Events.getBus().publish(new WaveSpawnEvent(wave));
        PlanePreset preset = wave.enemies.get(0);
        for (int i = 0; i < numberOfPlanes; i++) {
            Plane plane = PlaneFactory.build(preset);
            Events.getBus().publish(new AISpawnEvent(plane));
        }
    }

    @Handler
    public void handleEnemyPlaneSpawn(AISpawnEvent event) {
        ((AIController) event.getPlane().getController()).setWorld(world);
    }
}
