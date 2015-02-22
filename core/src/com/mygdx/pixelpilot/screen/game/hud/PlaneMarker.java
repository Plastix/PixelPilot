package com.mygdx.pixelpilot.screen.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.events.player.PlayerDeathEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.PlaneActor;
import com.mygdx.pixelpilot.event.events.PlaneMarkerSpawnEvent;
import com.mygdx.pixelpilot.util.Utils;


public class PlaneMarker extends Image implements Listener {

    private PlaneActor target;
    private Camera camera;

    public PlaneMarker(Plane target) {
        this.target = target.getPlaneActor();

        this.setSize(30, 30);
        this.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, Align.center);
        this.setVisible(false);
        this.setOrigin(Align.center);

        Texture marker = new Texture(Assets.image.plane_marker);
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(marker));
        Color color = target.getPlaneActor().getMarkerColor();
        this.setDrawable(drawable.tint(color));

        Events.emit(new PlaneMarkerSpawnEvent(this), this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (camera != null) {
            if (isTargetVisible()){
                this.setVisible(false);
            } else {
                this.setVisible(true);
            }
            Vector2 pos = new Vector2(target.getPosition()).sub(Utils.vec3d2d(camera.position));
            setMarkerPosition(pos);
            float angle = (float) (Math.toDegrees(Math.atan2(pos.y, pos.x)));
            this.setRotation(angle);
        }

    }

    private boolean isTargetVisible(){
        float stageX = target.getX();
        float stageY = target.getY();
        Rectangle actorRect = new Rectangle();
        Rectangle camRect = new Rectangle();

        actorRect.set(stageX, stageY, getWidth(), getHeight());
        camRect.set(camera.position.x - camera.viewportWidth / 2.0f, camera.position.y - camera.viewportHeight / 2.0f,
                camera.viewportWidth, camera.viewportHeight);
        return camRect.overlaps(actorRect);
    }

    private void setMarkerPosition(Vector2 pos){
        this.setPosition(pos.x, pos.y);

        if(pos.x < 0){
            this.setX(0);
        }
        if(pos.x > Gdx.graphics.getWidth()){
            this.setX(Gdx.graphics.getWidth() - this.getWidth());
        }
        if(pos.y < 0){
            this.setY(0);

        }
        if(pos.y > Gdx.graphics.getHeight()){
            this.setY(Gdx.graphics.getHeight() - this.getHeight());
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        this.setVisible(true);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        this.setVisible(false);
    }
}
