package com.mygdx.pixelpilot.screen.game.campaign;

import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.assetpack.CommonAssets;
import com.mygdx.pixelpilot.data.assetpack.GameAssets;
import com.mygdx.pixelpilot.data.level.Level;
import com.mygdx.pixelpilot.data.level.Wave;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.ai.AISpawnEvent;
import com.mygdx.pixelpilot.event.events.game.WaveSpawnEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.PlaneFactory;
import com.mygdx.pixelpilot.plane.PlanePreset;
import com.mygdx.pixelpilot.plane.controller.AIController;
import com.mygdx.pixelpilot.screen.game.GameScreen;
import com.mygdx.pixelpilot.screen.DependentBuilder;
import net.engio.mbassy.listener.Handler;

import java.util.List;

public class CampaignGameScreen extends GameScreen {

    private int currentLevel;
    private int currentWave;

    private CampaignGameScreen() {
        currentLevel = 0;
        currentWave = 0;
        setHUD(new CampaignHUD());
//        setDataSource(Assets.manager.get(Worlds.ISLAND));
        spawnPlayer();
        spawnWave(1);
    }

    public static class Loader extends DependentBuilder<CampaignGameScreen> {
        public Loader() {
            super(new CommonAssets(), new GameAssets());
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
