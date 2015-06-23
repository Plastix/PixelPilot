package com.mygdx.pixelpilot.screen.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.screen.loading.animation.LoadingAnimation;
import com.mygdx.pixelpilot.screen.DependentBuilder;

/**
 * A LoadingScreen is responsible for loading the resources from one or more AssetPacks
 */
public class LoadingScreen implements Screen {

    private Stage stage;
    private LoadingAnimation loader = null;
    private final DependentBuilder<? extends Screen> builder;

    public LoadingScreen(LoadingAnimation loader, DependentBuilder<? extends Screen> builder) {
        this.builder = builder;
        stage = new Stage(new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera()));
        this.loader = loader;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.66f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (!builder.getPacks().isEmpty() && builder.getPacks().peek().update()) {
            builder.getPacks().pop();
            if (builder.getPacks().isEmpty()) {
                Events.getBus().publish(new ScreenChangeEvent(builder));
            }
        }
        loader.render(delta, Assets.manager.getProgress(), stage);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
