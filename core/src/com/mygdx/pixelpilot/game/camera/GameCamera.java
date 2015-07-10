package com.mygdx.pixelpilot.game.camera;

import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.game.GamePauseEvent;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import net.engio.mbassy.listener.Handler;

public class GameCamera extends TrackingCamera {

    private boolean isPaused;

    public GameCamera() {
        super();
        isPaused = false;
        Events.getBus().subscribe(this);
    }

    @Override
    public void update() {
        if(!isPaused) {
            super.update();
        }
    }

    @Handler
    public void onPause(GamePauseEvent event){
        this.isPaused = true;
    }

    @Handler
    public void onResume(GameResumeEvent event){
        this.isPaused = false;
    }

//    @Handler
//    public void onPlaneSpawn(PlayerSpawnEvent event) {
//        this.track(event.getPlane());
//    }
}
