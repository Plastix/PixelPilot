package com.mygdx.pixelpilot.screen.game.hud;

import com.badlogic.gdx.graphics.Camera;
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
        this.setVisible(false);
        this.setOrigin(Align.center);

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
            //Convert the target plane's local actor coordinates to the stage's coordinates
            Vector2 targetPos = target.localToStageCoordinates(new Vector2(0, 0));
            if (isTargetVisible(targetPos)) {
                this.setVisible(false);
            } else {
                Vector2 cam = Utils.vec3d2d(camera.position);
                Vector2 diff = new Vector2(targetPos).sub(cam);
                float angle = (float) (Math.toDegrees(Math.atan2(diff.y, diff.x)));
                this.setRotation(angle);
                //Convert the target coordinates to screen cordinates
                setMarkerPosition(Utils.vec3d2d(camera.project(Utils.vec2d3d(targetPos))));
                this.setVisible(true);
            }
        }

    }

    private boolean isTargetVisible(Vector2 pos) {
        return camera.frustum.boundsInFrustum(pos.x, pos.y, 0, target.getImageWidth() / 2, target.getImageHeight() / 2, 0);
    }

    private void setMarkerPosition(Vector2 target){
        float width = this.getStage().getCamera().viewportWidth;
        float height = this.getStage().getCamera().viewportHeight;
        Rectangle bounds = new Rectangle(0, 0, width - getWidth(), height - getHeight());
        Vector2 pos = Utils.getBoundsEdgeIntersection(target, new Vector2(width / 2, height / 2), bounds);

        if(pos != null){
            this.setPosition(pos.x, pos.y);
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        this.remove();
    }
}
