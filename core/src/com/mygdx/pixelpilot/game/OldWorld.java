package com.mygdx.pixelpilot.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.MarkerSpawnEvent;
import com.mygdx.pixelpilot.event.events.ProjectileExpirationEvent;
import com.mygdx.pixelpilot.event.events.WeaponFireEvent;
import com.mygdx.pixelpilot.event.events.ai.AIDeathEvent;
import com.mygdx.pixelpilot.event.events.ai.AISpawnEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.game.plane.Plane;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.game.plane.controller.PlayerController;
import com.mygdx.pixelpilot.game.camera.GameCamera;
import com.mygdx.pixelpilot.util.quadtree.Quadtree;
import com.mygdx.pixelpilot.util.quadtree.QuadtreeCallback;
import net.engio.mbassy.listener.Handler;

import java.util.ArrayList;
import java.util.List;


public class OldWorld extends Stage {

    private final Rectangle bounds;
    private Actor backdrop;
    private Group clouds;
    private List<Plane> planes;
    private GameCamera camera;
    private int width, height;
    private Quadtree storage;

    private final int PLANE_LAYER = 2;
    private final int BULLET_LAYER = 1;

    public OldWorld(int width, int height) {
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


    }

    private void createBackdrop() {
        backdrop = Assets.manager.get("backdrop", Backdrop.class);
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
