package com.mygdx.pixelpilot.screen.game;

import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.events.game.GamePauseEvent;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;

public class GameCamera extends TrackingCamera implements Listener {

    private boolean isPaused;

    public GameCamera() {
        super();
        isPaused = false;
        Events.register(this);
    }

    @Override
    public void update() {
        if(!isPaused) {
            super.update();
        }
    }

    @EventHandler
    public void onPause(GamePauseEvent event){
        this.isPaused = true;
    }

    @EventHandler
    public void onResume(GameResumeEvent event){
        this.isPaused = false;
    }

    @EventHandler
    public void onPlaneSpawn(PlayerSpawnEvent event) {
        this.track(event.getPlane());
    }
}
