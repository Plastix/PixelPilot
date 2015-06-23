package com.mygdx.pixelpilot.screen.loading.animation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pixelpilot.data.Config;

public class SpinningAnimation extends LoadingAnimation {
    private ShapeRenderer sr;
    private float angle;

    public SpinningAnimation() {
        sr = new ShapeRenderer();
        angle = 0;
    }

    @Override
    public void render(float delta, float progress, Stage stage) {
        float len = Config.NativeView.width*2;
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setProjectionMatrix(stage.getCamera().combined);
        sr.identity();
        sr.setColor(new Color(0.9f, 0.92f, 0.36f, 1));
        int width = Config.NativeView.width;
        int height = Config.NativeView.height;
        sr.translate(width / 2, height/2, 0);
        sr.rotate(0, 0, 1, angle);
        sr.rectLine(-len / 2, 0, len / 2, 0, 25);
        sr.identity();
        sr.translate(width / 2, height/2, 0);
        sr.rotate(0, 0, 1, angle + 90);
        sr.rectLine(-len/2, 0, len/2, 0, 25);
        sr.end();
        angle += delta * 360f;
    }
}
