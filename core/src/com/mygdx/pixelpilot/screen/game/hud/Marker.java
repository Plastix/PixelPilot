package com.mygdx.pixelpilot.screen.game.hud;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.MarkerSpawnEvent;
import com.mygdx.pixelpilot.util.Utils;

public abstract class Marker<T extends Actor> extends Actor {

    protected T target;
    protected Vector3 targetPos; // Vector3 to work with camera
    protected Vector2 markerPos;
    protected Camera camera;
    protected Sprite sprite;
    private boolean render;

    public Marker(T target) {
        this.target = target;

        this.targetPos = new Vector3(target.getX(), target.getY(), 0);
        this.markerPos = new Vector2();
        this.render = false;

        this.sprite = createSprite();
        this.sprite.setOriginCenter();

        //World listens to this event and sets the Marker's camera to the world camera
        Events.emit(new MarkerSpawnEvent(this), this);
    }

    protected abstract Sprite createSprite();

    @Override
    public void act(float delta) {
        super.act(delta);
        if (camera != null) {
            targetPos.set(target.getX(), target.getY(), 0);
            if(!isTargetVisible(targetPos)) {
                this.render = true;
                Vector3 cam = camera.position;
                float angle = (float) (Math.toDegrees(Math.atan2(targetPos.y - cam.y, targetPos.x - cam.x)));
                this.sprite.setRotation(angle);
                //Convert the target coordinates to screen coordinates
                setMarkerPosition(camera.project(targetPos));
            }else if(render){
                this.render = false;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //Only draw the marker if the target is out of view
        if(render){
            this.sprite.draw(batch, parentAlpha);
        }
    }

    private boolean isTargetVisible(Vector3 pos) {
        return camera.frustum.boundsInFrustum(pos.x, pos.y, 0, target.getWidth() / 2, target.getHeight() / 2, 0);
    }

    private void setMarkerPosition(Vector3 target){
        float width = this.getStage().getCamera().viewportWidth;
        float height = this.getStage().getCamera().viewportHeight;

        Utils.intersectLineRect(markerPos, target.x, target.y, width / 2, height / 2, 0, 0, width - sprite.getWidth(), height - sprite.getHeight());
        this.sprite.setPosition(markerPos.x, markerPos.y);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}
