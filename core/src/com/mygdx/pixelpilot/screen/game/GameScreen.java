package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.game.GamePauseEvent;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.PlaneFactory;
import com.mygdx.pixelpilot.plane.PlanePreset;
import com.mygdx.pixelpilot.plane.controller.PlayerController;
import com.mygdx.pixelpilot.screen.game.hud.HUD;
import com.mygdx.pixelpilot.screen.menu.PauseMenu;
import net.engio.mbassy.listener.Handler;

public abstract class GameScreen extends ScreenAdapter {

    protected HUD hud;
    protected World world;
    private GameState state;

    public GameScreen(){
        this.state = GameState.PLAYING;

        world = new World(3000,3000);
        hud = new HUD();

        Events.getBus().subscribe(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(139f / 255f, 166f / 255f, 177f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (state != GameState.PAUSED) {
            world.act(delta);
            hud.act(delta);
        }
        world.draw();
        hud.draw();
    }

    @Override
    public void resize(int width, int height) {
        world.getViewport().update(width, height);
        hud.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        if(this.state != GameState.PAUSED) {
            Events.getBus().publish(new MenuOpenEvent(new PauseMenu()));
            Events.getBus().publish(new GamePauseEvent());
        }
    }

    @Override
    public void dispose() {
        world.dispose();
        hud.dispose();
    }

    protected void spawnPlayer(){
        //TODO Eventually get the player's selected plane
        PlanePreset preset = GameData.planePresets.get(0);
        Plane player = PlaneFactory.build(preset, PlayerController.class);
        Events.getBus().publish(new PlayerSpawnEvent(player));
    }

    @Handler
    public void onPause(GamePauseEvent event){
        this.state = GameState.PAUSED;
    }

    @Handler
    public void onResume(GameResumeEvent event){
        this.state = GameState.PLAYING;
    }
}
