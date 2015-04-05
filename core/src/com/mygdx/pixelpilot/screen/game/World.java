package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.ai.AISpawnEvent;
import com.mygdx.pixelpilot.event.events.PlaneMarkerSpawnEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.controller.PlayerController;
import com.mygdx.pixelpilot.util.quadtree.Quadtree;
import com.mygdx.pixelpilot.util.quadtree.QuadtreeCallback;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;

import java.util.ArrayList;
import java.util.List;


public class World extends Stage implements Listener {

    private final Rectangle bounds;
    private Actor backdrop;
    private Group clouds;
    private List<Plane> planes;
    private TrackingCamera camera;
    private int width, height;
    private Quadtree storage;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(0, 0, width, height);
        this.camera = new TrackingCamera();
        this.camera.setWorldBounds(bounds);
        this.setViewport(new ExtendViewport(Config.NativeView.width, Config.NativeView.height));
        this.getViewport().setCamera(camera);
        this.storage = new Quadtree(0, 0, width, height);
        Events.register(this);
//        clouds = Cloud.generateClouds(150); // static for now
        planes = new ArrayList<Plane>();
        createBackdrop();
    }

    private void createBackdrop() {
        BackdropTheme theme = BackdropFactory.buildTheme(BackdropFactory.ThemePreset.ISLANDS);
        backdrop = BackdropFactory.buildBackdrop(width, height, 4, theme);
        backdrop.setSize(width, height);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void draw() {
        getBatch().begin();
        backdrop.draw(getBatch(), 1);
        getBatch().end();
        super.draw();
        getRoot().setCullingArea(camera.getViewportBounds());
        storage.update(); // gotta be a better place to put this...
        storage.draw(camera);
    }

    /**
     * Finds a position outside of the player's viewport at which to spawn a plane
     *
     * @return the calculated Vector2
     */
    private Vector2 getNewSpawnPosition() {
        return new Vector2(MathUtils.random(0, width), MathUtils.random(0, height));
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int get(Rectangle box, QuadtreeCallback cb) {
        return storage.get(box, cb);
    }

    private void addPlane(Plane plane, Vector2 spawnPos) {
        plane.setPosition(spawnPos.x, spawnPos.y);
        this.addActor(plane);
        storage.insert(plane);
        planes.add(plane);
    }

    @EventHandler
    public void onPlaneSpawn(AISpawnEvent event) {
        addPlane(event.getPlane(), getNewSpawnPosition());
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnEvent event) {
        addPlane(event.getPlane(), getNewSpawnPosition());
        ((PlayerController) (event.getPlane().getController())).setWorld(this);
    }

    @EventHandler
    public void onPlayerMarkerSpawn(PlaneMarkerSpawnEvent event) {
        event.getPlaneMarker().setCamera(camera);
    }

}
