package com.mygdx.skystorm.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.skystorm.event.EventHandler;
import com.mygdx.skystorm.event.Events;
import com.mygdx.skystorm.event.Listener;
import com.mygdx.skystorm.event.events.PlaneSpawnEvent;
import com.mygdx.skystorm.util.Utils;

public class GameCamera implements Listener{
    Camera camera;
    Actor target;


    GameCamera(Camera camera) {
        this.camera = camera;
        Events.register(this);
    }

    public void track(Actor target) {
        this.target = target;
    }

    public void update(float delta) {
        // straight tracking

        if(target != null) {
            Vector3 currPos = camera.position;
            Vector3 targetPos = new Vector3(target.getX(), target.getY(), 0);
            currPos.interpolate(targetPos, 0.1f, Interpolation.linear);
        }
    }
    
    @EventHandler
    public void onPlaneSpawn(PlaneSpawnEvent event){
        track(event.getPlane().getActor());
    }

}
