package com.mygdx.pixelpilot.screen.loading.animation;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class LoadingAnimation {
    public abstract void render(float delta, float progress, Stage stage);
}
