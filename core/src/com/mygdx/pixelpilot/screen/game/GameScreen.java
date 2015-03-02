package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pixelpilot.PixelPilot;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.events.game.GamePauseEvent;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.PlaneFactory;
import com.mygdx.pixelpilot.plane.PlanePreset;
import com.mygdx.pixelpilot.plane.controller.PlayerController;

import java.util.ArrayList;
import java.util.List;

public abstract class GameScreen implements Screen, Listener {

    protected final PixelPilot game;
    private List<Stage> stages;
    private GameState state;

    public GameScreen(PixelPilot game){
        this.game = game;
        stages = new ArrayList<Stage>();
        this.state = GameState.PLAYING;
        Events.register(this);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(139f / 255f, 166f / 255f, 177f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(Stage stage : stages) {
            if(state != GameState.PAUSED) {
                stage.act(delta);
            }
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

    protected void spawnPlayer(){
        //TODO Eventually get the player's selected plane
        PlanePreset preset = GameData.planePresets.get(0);
        Plane player = PlaneFactory.build(preset, PlayerController.class);
        Events.emit(new PlayerSpawnEvent(player), this);
    }

    protected void addStage(Stage stage){
        this.stages.add(stage);
    }

    protected void removeState(Stage stage){
        if(this.stages.contains(stage)){
            stages.remove(stage);
        }
    }

    @EventHandler
    public void onPause(GamePauseEvent event){
        this.state = GameState.PAUSED;
        PauseOverlay pause = new PauseOverlay();
        this.addStage(pause);
        pause.act();
    }

    @EventHandler
    public void onResume(GameResumeEvent event){
        this.state = GameState.PLAYING;
        removeState(event.getStage());
    }
}
