package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.PlaneSpawnEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.util.Utils;
import com.mygdx.pixelpilot.world.Cloud;
import com.mygdx.pixelpilot.world.background.theme.BackdropFactory;
import com.mygdx.pixelpilot.world.background.theme.BackdropTheme;
import com.mygdx.pixelpilot.event.events.PlaneMarkerSpawnEvent;

import java.util.ArrayList;
import java.util.List;


public class World extends Stage implements Listener {

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
        this.setViewport(new ExtendViewport(960, 540));
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

    private void addPlane(Plane plane, Vector2 spawnPos){
        plane.setPosition(spawnPos.x, spawnPos.y);
        this.addActor(plane);
        planes.add(plane);
    }

    @EventHandler
    public void onPlaneSpawn(PlaneSpawnEvent event){
        addPlane(event.getPlane(), getNewSpawnPosition());
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnEvent event){
        addPlane(event.getPlane(), new Vector2(0,0));
    }

    @EventHandler
    public void onPlayerMarkerSpawn(PlaneMarkerSpawnEvent event){
        event.getPlaneMarker().setCamera(camera);
    }

    /**
     * Finds a position outside of the player's viewport at which to spawn a plane
     * @return the calculated Vector2
     */
    private Vector2 getNewSpawnPosition(){
        return new Vector2(Utils.randomInt(100, 2000), Utils.randomInt(100, 2000));
    }
}
