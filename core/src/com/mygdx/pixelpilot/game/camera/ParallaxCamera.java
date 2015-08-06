package com.mygdx.pixelpilot.game.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 * Orthographic camera that supports Parallax operations
 * Code from https://github.com/libgdx/libgdx/blob/master/tests/gdx-tests/src/com/badlogic/gdx/tests/ParallaxTest.java
 */
public class ParallaxCamera extends OrthographicCamera {

    private Matrix4 parallaxView = new Matrix4();
    private Matrix4 parallaxCombined = new Matrix4();
    private Vector3 tmp = new Vector3();
    private Vector3 tmp2 = new Vector3();

    public Matrix4 calculateParallaxMatrix(float parallaxX, float parallaxY) {
        update();
        tmp.set(position);
        tmp.x *= parallaxX;
        tmp.y *= parallaxY;

        parallaxView.setToLookAt(tmp, tmp2.set(tmp).add(direction), up);
        parallaxCombined.set(projection);
        Matrix4.mul(parallaxCombined.val, parallaxView.val);
        return parallaxCombined;
    }
}
