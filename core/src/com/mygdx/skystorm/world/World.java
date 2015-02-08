package com.mygdx.skystorm.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.skystorm.event.Listener;
import com.mygdx.skystorm.event.EventHandler;
import com.mygdx.skystorm.event.Events;
import com.mygdx.skystorm.event.events.PlaneSpawnEvent;
import com.mygdx.skystorm.plane.Plane;

import java.util.ArrayList;
import java.util.List;


public class World extends Group implements Listener {

    private Actor backdrop;
    private Group clouds;
    private List<Plane> planes;

    public World(int width, int height) {
        this.setSize(width, height);
        Events.register(this);
        clouds = Cloud.generateClouds(150); // static for now
        planes = new ArrayList<Plane>();
    }

    public void setBackdrop(Actor bg){
        backdrop = bg;
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

    @EventHandler
    public void onPlaneSpawn(PlaneSpawnEvent event){
        System.out.println("OMG A PLANE SPAWNED!");
        Plane plane = event.getPlane();
        Vector2 spawnPosition = getNewSpawnPosition();
        plane.setPosition(spawnPosition.x, spawnPosition.y);
        this.addActor(plane);
        planes.add(plane);
    }


    /**
     * Finds a position outside of the player's viewport at which to spawn a plane
     * @return the calculated Vector2
     */
    private Vector2 getNewSpawnPosition(){
        return new Vector2(0,0);
    }
}
