package com.mygdx.pixelpilot.screen.game.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.events.ai.AIDeathEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerDeathEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.event.events.PlaneMarkerSpawnEvent;
import com.mygdx.pixelpilot.util.Utils;


public class PlaneMarker extends Image implements Listener {

    private Plane target;
    private Camera camera;
    private Vector3 targetPos; // Vector3 to work with camera
    private Vector2 markerPos;

    public PlaneMarker(Plane target) {
        this.target = target;
        this.targetPos = new Vector3(target.getX(), target.getY(), 0);
        this.markerPos = new Vector2();
        this.setSize(30, 30);
        this.setVisible(false);
        this.setOrigin(Align.center);
        Events.register(this);

        Texture marker = new Texture(Assets.image.plane_marker);
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(marker));
        this.setDrawable(drawable.tint(this.target.getMarkerColor()));

        //World listens to this event and sets the PlaneMarker's camera to the world camera
        Events.emit(new PlaneMarkerSpawnEvent(this), this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (camera != null) {
            targetPos.set(target.getX(), target.getY(), 0);

            if (isTargetVisible(targetPos)) {
                this.setVisible(false);
            } else {
                Vector3 cam = camera.position;
                float angle = (float) (Math.toDegrees(Math.atan2(targetPos.y - cam.y, targetPos.x - cam.x)));
                this.setRotation(angle);
                //Convert the target coordinates to screen coordinates
                setMarkerPosition(camera.project(targetPos));
                this.setVisible(true);
            }
        }

    }

    private boolean isTargetVisible(Vector3 pos) {
        return camera.frustum.boundsInFrustum(pos.x, pos.y, 0, target.getWidth() / 2, target.getHeight() / 2, 0);
    }

    private void setMarkerPosition(Vector3 target){
        float width = this.getStage().getCamera().viewportWidth;
        float height = this.getStage().getCamera().viewportHeight;

        Utils.intersectLineRect(markerPos, target.x, target.y, width/2, height/2, 0, 0, width - getWidth(), height - getHeight());
        this.setPosition(markerPos.x, markerPos.y);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @EventHandler
    public void onAIDeath(AIDeathEvent event) {
        if(event.getPlane().equals(this.target)) {
            this.target = null;
            this.remove();
        }
    }
}
