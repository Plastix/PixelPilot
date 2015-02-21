package com.mygdx.skystorm.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.skystorm.SkyStorm;

import java.util.ArrayList;
import java.util.List;

public abstract class GameScreen implements Screen {

    protected final SkyStorm game;
    private List<Stage> stages;

    public GameScreen(SkyStorm game){
        this.game = game;
        stages = new ArrayList<Stage>();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(139f / 255f, 166f / 255f, 177f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(Stage stage : stages) {
            stage.act(delta);
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        for(Stage stage : stages) {
            stage.getViewport().update(width, height, true);
        }
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
        for(Stage stage : stages) {
            stage.dispose();
        }
    }

    public void addStage(Stage stage){
        this.stages.add(stage);
    }
}
