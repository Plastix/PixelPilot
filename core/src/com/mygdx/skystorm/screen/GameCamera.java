package com.mygdx.skystorm.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameCamera {
    Camera camera;
    Actor target;

    GameCamera(Camera camera) {
        this.camera = camera;
    }

    public void track(Actor target) {
        this.target = target;
    }

    public void update() {
        // straight tracking
        if(target != null) {
            camera.translate(target.getX() - camera.position.x, target.getY() - camera.position.y, 0);
        }

    }

}
