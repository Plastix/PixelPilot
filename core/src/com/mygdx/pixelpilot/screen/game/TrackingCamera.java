package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.util.Utils;

public class TrackingCamera extends OrthographicCamera
        implements Listener {
    private Actor target;
    private Rectangle bounds;
    private float baseTrackSpeed;
    private Interpolation interpolator;

    public TrackingCamera() {
        this.baseTrackSpeed = 0.15f;
        this.bounds = new Rectangle();
        this.interpolator = Interpolation.linear;
        Events.register(this);
    }

    public void track(Actor target) {
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
    }

    public void setWorldBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    private void track() {
        Vector3 currPos = position;
        Vector2 targetPos2D = Utils.positionVector(target);
        Vector3 targetPos = Utils.vec2d3d(targetPos2D);

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
        float leftHardBorder = bounds.x;
        float leftSoftBorder = leftHardBorder + borderPaddingX;
        float rightHardBorder = bounds.x + bounds.width;
        float rightSoftBorder = rightHardBorder - borderPaddingX;
        if (cameraLeftEdge < leftSoftBorder && position.x > targetPos.x) {
            float distToLeftHardBorder = cameraLeftEdge - leftHardBorder;
            float amount = Utils.map(distToLeftHardBorder, 0, leftSoftBorder - leftHardBorder, 0.002f, baseTrackSpeed);
            currPos.x = interpolator.apply(currPos.x, targetPos.x, amount);
            // final safety check (can't use the variables because the corresponding camera values have changed)
            if (position.x - viewportWidth / 2f < leftHardBorder) {
                position.x = bounds.x + viewportWidth / 2f;
            }
        } else if (cameraRightEdge > rightSoftBorder && position.x < targetPos.x) {
            float distToRightHardBorder = rightHardBorder - cameraRightEdge;
            float amount = Utils.map(distToRightHardBorder, 0, rightHardBorder - rightSoftBorder, 0.002f, baseTrackSpeed);
            currPos.x = interpolator.apply(currPos.x, targetPos.x, amount);
            if (position.x + viewportWidth / 2f > rightHardBorder) {
                position.x = bounds.x + bounds.width - viewportWidth / 2f;
            }
        } else { // normal movement
            currPos.x = interpolator.apply(currPos.x, targetPos.x, baseTrackSpeed);
        }

        float borderPaddingY = viewportHeight / 3.5f;
        float cameraBottomEdge = position.y - viewportHeight / 2f;
        float cameraTopEdge = position.y + viewportHeight / 2f;
        float bottomHardBorder = bounds.y;
        float bottomSoftBorder = bottomHardBorder + borderPaddingY;
        float topHardBorder = bounds.y + bounds.height;
        float topSoftBorder = topHardBorder - borderPaddingY;
        if (cameraBottomEdge < bottomSoftBorder && position.y > targetPos.y) {
            float distToBottomHardBorder = cameraBottomEdge - bottomHardBorder;
            float amount = Utils.map(distToBottomHardBorder, 0, bottomSoftBorder - bottomHardBorder, 0.002f, baseTrackSpeed);
            currPos.y = interpolator.apply(currPos.y, targetPos.y, amount);
            if (position.y - viewportHeight / 2f < bottomHardBorder) {
                position.y = bounds.y + viewportHeight / 2f;
            }
        } else if (cameraTopEdge > topSoftBorder && position.y < targetPos.y) {
            float distToTopHardBorder = topHardBorder - cameraTopEdge;
            float amount = Utils.map(distToTopHardBorder, 0, topHardBorder - topSoftBorder, 0.002f, baseTrackSpeed);
            currPos.y = interpolator.apply(currPos.y, targetPos.y, amount);
            if (position.y + viewportHeight / 2f > topHardBorder) {
                position.y = bounds.y + bounds.height - viewportHeight / 2f;
            }
        } else {
            currPos.y = interpolator.apply(currPos.y, targetPos.y, baseTrackSpeed);
        }
    }

    @EventHandler
    public void onPlaneSpawn(PlayerSpawnEvent event) {
        track(event.getPlane());
    }
}
