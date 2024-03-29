package com.mygdx.pixelpilot.game.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.util.Utils;
import net.engio.mbassy.listener.Handler;

public class TrackingCamera extends OrthographicCamera {

    private Position target;
    private Rectangle worldBounds;
    private float baseTrackSpeed;
    private Interpolation interpolator;
    private Rectangle viewportBounds;

    public TrackingCamera() {
        this.baseTrackSpeed = 0.15f;
        this.worldBounds = new Rectangle();
        this.interpolator = Interpolation.linear;
        this.viewportBounds = new Rectangle();
        Events.getBus().subscribe(this);
    }

    public void track(Position target) {
        this.target = target;
    }

    @Override
    public void update() {
        super.update();
        if(Gdx.input.isKeyPressed(Input.Keys.I))
            zoom -= 0.1f;
        else if(Gdx.input.isKeyPressed(Input.Keys.J))
            zoom += 0.1f;
        if (target != null) {
            track();
        }
        viewportBounds.set(position.x-viewportWidth*zoom/2,
                position.y-viewportHeight*zoom/2, viewportWidth*zoom, viewportHeight*zoom);
    }

    public void setWorldBounds(Rectangle bounds) {
        this.worldBounds = bounds;
    }

    public Rectangle getWorldBounds() {
        return worldBounds;
    }

    public Rectangle getViewportBounds() {
        return viewportBounds;
    }

    private void track() {
        Vector3 currPos = position;

        float targetX = target.x;
        float targetY = target.y;
            /*
            * Explanation semi-attempt:
            *   When the camera's leading edge passes the soft border, the lerp speed
            *      decreases as a function of the remaining distance to the hard border
            *      This only happens if the plane is heading towards the hard border
            *      otherwise it lerps as normal
            * */

        /* here be dragons. */
        float borderPaddingX = viewportWidth / 3f;
        float cameraLeftEdge = position.x - viewportWidth / 2f;
        float cameraRightEdge = position.x + viewportWidth / 2f;
        float leftHardBorder = worldBounds.x;
        float leftSoftBorder = leftHardBorder + borderPaddingX;
        float rightHardBorder = worldBounds.x + worldBounds.width;
        float rightSoftBorder = rightHardBorder - borderPaddingX;
        if (cameraLeftEdge < leftSoftBorder && position.x > targetX) {
            float distToLeftHardBorder = cameraLeftEdge - leftHardBorder;
            float amount = Utils.map(distToLeftHardBorder, 0, leftSoftBorder - leftHardBorder, 0.002f, baseTrackSpeed);
            currPos.x = interpolator.apply(currPos.x, targetX, amount);
            // final safety check (can't use the variables because the corresponding camera values have changed)
            if (position.x - viewportWidth / 2f < leftHardBorder) {
                position.x = worldBounds.x + viewportWidth / 2f;
            }
        } else if (cameraRightEdge > rightSoftBorder && position.x < targetX) {
            float distToRightHardBorder = rightHardBorder - cameraRightEdge;
            float amount = Utils.map(distToRightHardBorder, 0, rightHardBorder - rightSoftBorder, 0.002f, baseTrackSpeed);
            currPos.x = interpolator.apply(currPos.x, targetX, amount);
            if (position.x + viewportWidth / 2f > rightHardBorder) {
                position.x = worldBounds.x + worldBounds.width - viewportWidth / 2f;
            }
        } else { // normal movement
            currPos.x = interpolator.apply(currPos.x, targetX, baseTrackSpeed);
        }

        float borderPaddingY = viewportHeight / 3.5f;
        float cameraBottomEdge = position.y - viewportHeight / 2f;
        float cameraTopEdge = position.y + viewportHeight / 2f;
        float bottomHardBorder = worldBounds.y;
        float bottomSoftBorder = bottomHardBorder + borderPaddingY;
        float topHardBorder = worldBounds.y + worldBounds.height;
        float topSoftBorder = topHardBorder - borderPaddingY;
        if (cameraBottomEdge < bottomSoftBorder && position.y > targetY) {
            float distToBottomHardBorder = cameraBottomEdge - bottomHardBorder;
            float amount = Utils.map(distToBottomHardBorder, 0, bottomSoftBorder - bottomHardBorder, 0.002f, baseTrackSpeed);
            currPos.y = interpolator.apply(currPos.y, targetY, amount);
            if (position.y - viewportHeight / 2f < bottomHardBorder) {
                position.y = worldBounds.y + viewportHeight / 2f;
            }
        } else if (cameraTopEdge > topSoftBorder && position.y < targetY) {
            float distToTopHardBorder = topHardBorder - cameraTopEdge;
            float amount = Utils.map(distToTopHardBorder, 0, topHardBorder - topSoftBorder, 0.002f, baseTrackSpeed);
            currPos.y = interpolator.apply(currPos.y, targetY, amount);
            if (position.y + viewportHeight / 2f > topHardBorder) {
                position.y = worldBounds.y + worldBounds.height - viewportHeight / 2f;
            }
        } else {
            currPos.y = interpolator.apply(currPos.y, targetY, baseTrackSpeed);
        }
    }

//    @Handler
//    public void onPlaneSpawn(PlayerSpawnEvent event) {
//        track(event.getPlane());
//    }
}
