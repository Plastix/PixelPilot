package com.mygdx.pixelpilot.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.data.YamlParser;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;
import com.mygdx.pixelpilot.screen.menu.MainMenu;
import com.mygdx.pixelpilot.screen.menu.MenuScreen;

/**
 * Main loading screen of the game
 */
public class SplashScreen implements Screen {

    private Stage stage;

    public SplashScreen() {
        stage = new Stage(new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera()));
        ControllerFactory.registerControllers();
        YamlParser.loadAllData();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        //If all the assets are done loading switch to the main menu
        if(Assets.update()){
            Events.emit(new ScreenChangeEvent(new MenuScreen()), this);
            Events.emit(new MenuOpenEvent(new MainMenu()), this);
        }
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
