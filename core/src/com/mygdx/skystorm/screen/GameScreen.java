package com.mygdx.skystorm.screen;

import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.event.*;
import com.mygdx.skystorm.event.events.PlaneDeathEvent;
import com.mygdx.skystorm.event.events.PlaneSpawnEvent;
import com.mygdx.skystorm.plane.*;
import com.mygdx.skystorm.plane.controller.PlayerController;
import com.mygdx.skystorm.screen.ui.HUD;
import com.mygdx.skystorm.world.World;
import com.mygdx.skystorm.world.background.Backdrop;
import com.mygdx.skystorm.world.background.theme.BackdropFactory;
import com.mygdx.skystorm.world.background.theme.BackdropTheme;

/**
 * A class for a game screen without a mode
 */
public class GameScreen extends ActionScreen implements Listener {

    private World world;
    private HUD hud;
    private GameCamera camera;


    @EventHandler
    public void onPlaneDeath(PlaneDeathEvent event){
        Plane p = event.getPlane();
        System.out.println("My first event handler!");
    }

    public GameScreen(SkyStorm game) {
        super(game);
        Events.register(this);
        camera = new GameCamera(stage.getCamera());
        hud = new HUD();
        world = new World(3000, 3000);
        BackdropTheme theme = BackdropFactory.buildTheme(BackdropFactory.ThemePreset.ISLANDS);
        Backdrop bg = BackdropFactory.buildBackdrop((int)world.getHeight(), (int)world.getWidth(), 4, theme);
        world.setBackdrop(bg);
        stage.addActor(world);
        stage.addActor(hud);


        // let's spawn some planes!
        PlaneDefinition definition = GameData.planeDefinitions.get(0);
        WeaponDefinition weapon = GameData.weaponDefinitions.get(0);
        Plane planeToSpawn = PlaneFactory.build(definition, weapon, new PlayerController());
        Events.emit(new PlaneSpawnEvent(planeToSpawn));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

}
