package com.mygdx.pixelpilot.game.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class ParallaxUtil {

    public static Matrix4 calculateParallaxMatrix(Camera camera, float parallaxX, float parallaxY) {
        Matrix4 parallaxView = new Matrix4();
        Matrix4 parallaxCombined = new Matrix4();
        Vector3 tmp = new Vector3();
        Vector3 tmp2 = new Vector3();
        camera.update();
        tmp.set(camera.position);
        tmp.x *= parallaxX;
        tmp.y *= parallaxY;

        parallaxView.setToLookAt(tmp, tmp2.set(tmp).add(camera.direction), camera.up);
        parallaxCombined.set(camera.projection);
        Matrix4.mul(parallaxCombined.val, parallaxView.val);
        return parallaxCombined;
    }
}
