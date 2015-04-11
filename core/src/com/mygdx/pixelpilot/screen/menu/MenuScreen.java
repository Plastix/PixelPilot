package com.mygdx.pixelpilot.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.effect.Cloud;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MenuScreen extends ScreenAdapter {

    protected final Stage bg;

    public MenuScreen() {
        ExtendViewport view = new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera());
        bg = new Stage(view);
        createBackground();
        createClouds();
    }

    private void createClouds(){
        Group clouds = Cloud.generateClouds(15);
        for(Actor a : clouds.getChildren()){
            MoveToAction slideAcrossScreen = new MoveToAction();
            slideAcrossScreen.setPosition(1000, a.getY());
            slideAcrossScreen.setDuration((float)((Math.random() * 200) + 100));
            a.addAction(forever(sequence(slideAcrossScreen, moveTo(-1000, a.getY()))));
        }
        bg.addActor(clouds);
    }

    private void createBackground(){
        BackdropTheme theme = BackdropFactory.buildTheme(BackdropFactory.ThemePreset.ISLANDS);
        Backdrop background = BackdropFactory.buildBackdrop(Config.NativeView.width, Config.NativeView.height, 4, theme);
        bg.addActor(background);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(139f / 255f, 166f / 255f, 177f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bg.act(delta);
        bg.draw();
    }

    @Override
    public void resize(int width, int height) {
        bg.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
