package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;
import com.mygdx.pixelpilot.effect.Cloud;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.ProjectileExpirationEvent;
import com.mygdx.pixelpilot.event.events.WeaponFireEvent;
import com.mygdx.pixelpilot.event.events.ai.AIDeathEvent;
import com.mygdx.pixelpilot.event.events.ai.AISpawnEvent;
import com.mygdx.pixelpilot.event.events.MarkerSpawnEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.controller.PlayerController;
import com.mygdx.pixelpilot.plane.armaments.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.util.quadtree.Quadtree;
import com.mygdx.pixelpilot.util.quadtree.QuadtreeCallback;
import net.engio.mbassy.listener.Handler;

import java.util.ArrayList;
import java.util.List;


public class World extends Stage {

    private final Rectangle bounds;
    private Backdrop backdrop;
    private Group clouds;
    private List<Plane> planes;
    private GameCamera camera;
    private int width, height;
    private Quadtree storage;

    private static int BACKDROP_LAYER = 1;
    private static int CLOUD_LAYER = 2;
    private final int BULLET_LAYER = 3;
    private static int PLANE_LAYER = 4;

    public World(int width, int height) {
        super(new ExtendViewport(Config.NativeView.width, Config.NativeView.height));
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(0, 0, width, height);
        this.camera = new GameCamera();
        this.camera.setWorldBounds(bounds);
        this.setViewport(new ExtendViewport(Config.NativeView.width, Config.NativeView.height));
        this.getViewport().setCamera(camera);
        //We need to update the viewport after giving it a new camera
        this.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        this.storage = new Quadtree(0, 0, width, height);
        Events.getBus().subscribe(this);
//        clouds = Cloud.generateClouds(150); // static for now
        planes = new ArrayList<Plane>();
        createBackdrop();
        createClouds();
    }

    private void createBackdrop() {
        BackdropTheme theme = BackdropFactory.buildTheme(BackdropFactory.ThemePreset.ISLANDS);
        backdrop = BackdropFactory.buildBackdrop(width, height, 4, theme);
        backdrop.setSize(width, height);
        backdrop.setZIndex(BACKDROP_LAYER);
    }

    private void createClouds(){
        for(int i = 0; i < 100; i++){
            Cloud cloud = new Cloud((float)(Math.random() * width), (float)(Math.random() * height), 50, 50, 10);
            cloud.setZIndex(CLOUD_LAYER);
            this.addActor(cloud);
        }
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
//        storage.draw(camera);
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

    public Quadtree getStorage() {
        return storage;
    }

    private void addPlane(Plane plane, Vector2 spawnPos) {
        plane.setPosition(spawnPos.x, spawnPos.y);
        plane.setZIndex(PLANE_LAYER);
        this.addActor(plane);
        plane.toFront();
        storage.insert(plane);
        planes.add(plane);
    }

    private void addProjectile(Projectile projectile) {

        this.addActor(projectile);
        projectile.toBack();
        storage.insert(projectile);
    }

    @Handler
    public void onPlaneSpawn(AISpawnEvent event) {
        addPlane(event.getPlane(), getNewSpawnPosition());
    }

    @Handler
    public void onPlayerSpawn(PlayerSpawnEvent event) {
        addPlane(event.getPlane(), getNewSpawnPosition());
        ((PlayerController) (event.getPlane().getController())).setWorld(this);
    }

    @Handler
    public void onMarkerSpawn(MarkerSpawnEvent event) {
        event.getMarker().setCamera(camera);
    }

    @Handler
    public void onAIDeath(AIDeathEvent event) {
        Plane plane = event.getPlane();
        storage.remove(plane);
        planes.remove(plane);
        plane.remove();
    }

    @Handler
    public void onWeaponFire(WeaponFireEvent event) {
        addProjectile(event.getProjectile());
    }

    @Handler
    public void onProjectileExpire(ProjectileExpirationEvent event) {
        Projectile p = event.getProjectile();
        storage.remove(p);
        p.remove();
    }
}
