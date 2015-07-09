package com.mygdx.pixelpilot.game;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.pixelpilot.game.system.MovementSystem;
import com.mygdx.pixelpilot.game.system.PlayerInputSystem;
import com.mygdx.pixelpilot.game.system.RenderSystem;
import com.mygdx.pixelpilot.game.system.WaveSystem;

public abstract class GameScreen extends ScreenAdapter {

    //    protected HUD hud;
//    protected OldWorld world;
//    private GameState state;
    protected final World world;

    public GameScreen() {

        world = new World();
        world.setSystem(new RenderSystem());
        world.setSystem(new MovementSystem());
        world.setSystem(new PlayerInputSystem());
        world.setSystem(new WaveSystem());

        world.initialize();
    }

//    protected void setWorld(OldWorld world) {
//        this.world = world;
//    }

//    protected void setHUD(HUD hud){
//        this.hud = hud;
//    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(139f / 255f, 166f / 255f, 177f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.setDelta(delta);
        world.process();

//        if (state != GameState.PAUSED) {
//            world.act(delta);
//            hud.act(delta);
//        }
////        world.draw();
//        hud.draw();
    }

    @Override
    public void resize(int width, int height) {
//        world.getViewport().update(width, height);
//        hud.getViewport().update(width, height);
    }

    @Override
    public void pause() {
//        if (this.state != GameState.PAUSED) {
//            Events.getBus().publish(new MenuOpenEvent(new PauseMenu.Loader()));
//            Events.getBus().publish(new GamePauseEvent());
//        }
    }

    @Override
    public void dispose() {
//        world.dispose();
//        hud.dispose();
    }

//    protected void spawnPlayer() {
//        //TODO Eventually get the player's selected plane
//        PlanePreset preset = GameData.planePresets.get(0);
//        Plane player = PlaneFactory.build(preset, PlayerController.class);
//        Events.getBus().publish(new PlayerSpawnEvent(player));
//    }

//    @Handler
//    public void onPause(GamePauseEvent event) {
//        this.state = GameState.PAUSED;
//    }
//
//    @Handler
//    public void onResume(GameResumeEvent event) {
//        this.state = GameState.PLAYING;
//    }
}
