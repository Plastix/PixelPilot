package com.mygdx.skystorm.screen.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.skystorm.event.Listener;
import com.mygdx.skystorm.event.EventHandler;
import com.mygdx.skystorm.event.Events;
import com.mygdx.skystorm.event.events.PlaneSpawnEvent;
import com.mygdx.skystorm.event.events.player.PlayerSpawnEvent;
import com.mygdx.skystorm.plane.Plane;
import com.mygdx.skystorm.plane.controller.PlayerController;
import com.mygdx.skystorm.world.Cloud;
import com.mygdx.skystorm.world.background.theme.BackdropFactory;
import com.mygdx.skystorm.world.background.theme.BackdropTheme;

import java.util.ArrayList;
import java.util.List;


public class World extends com.badlogic.gdx.scenes.scene2d.Stage implements Listener {

    private Actor backdrop;
    private Group clouds;
    private List<Plane> planes;
    private int width, height;

    private TrackingCamera camera;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        camera = new TrackingCamera();
        camera.setWorldBounds(0, 0, width, height);
        this.getViewport().setCamera(camera);

        Events.register(this);
        clouds = Cloud.generateClouds(150); // static for now
        planes = new ArrayList<Plane>();
        createBackdrop();
    }

    private void createBackdrop(){
        BackdropTheme theme = BackdropFactory.buildTheme(BackdropFactory.ThemePreset.ISLANDS);
        backdrop = BackdropFactory.buildBackdrop(width, height, 4, theme);
        backdrop.setSize(width, height);
        backdrop.setScale(4);
        this.addActor(backdrop);
    }

    private void addPlane(Plane plane){
        Vector2 spawnPosition = getNewSpawnPosition();
        plane.setPosition(spawnPosition.x, spawnPosition.y);
        this.addActor(plane);
        planes.add(plane);
    }

    @EventHandler
    public void onPlaneSpawn(PlaneSpawnEvent event){
        addPlane(event.getPlane());
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnEvent event){
        addPlane(event.getPlane());
    }


    /**
     * Finds a position outside of the player's viewport at which to spawn a plane
     * @return the calculated Vector2
     */
    private Vector2 getNewSpawnPosition(){
        return new Vector2(0,0);
    }
}
