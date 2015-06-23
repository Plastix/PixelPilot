package com.mygdx.pixelpilot.screen.loading.animation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pixelpilot.data.Config;

public class BarAnimation extends LoadingAnimation {
    private ShapeRenderer sr = new ShapeRenderer();
    private float currentProgress = 0;

    @Override
    public void render(float delta, float progress, Stage stage) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setProjectionMatrix(stage.getCamera().combined);
        sr.setColor(new Color(0.9f, 0.92f, 0.36f, 1));
        int width = Config.NativeView.width;
        int height = Config.NativeView.height;
        currentProgress = MathUtils.lerp(currentProgress, (width - 50) * progress, 0.2f);
        sr.rect(50, height / 2 - 20, currentProgress, 40);
        sr.end();
    }
}
