package com.mygdx.skystorm.screen.game;

import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.event.*;
import com.mygdx.skystorm.event.events.PlaneSpawnEvent;
import com.mygdx.skystorm.event.events.player.PlayerSpawnEvent;
import com.mygdx.skystorm.plane.*;
import com.mygdx.skystorm.plane.controller.PlayerController;
import com.mygdx.skystorm.screen.game.hud.HUD;

public class CampaignGameScreen extends GameScreen implements Listener {

    private World world;
    private HUD hud;

    public CampaignGameScreen(SkyStorm game) {
        super(game);
        hud = new HUD();
        world = new World(3000, 3000);

        addStage(world);
        addStage(hud);

        // let's spawn some planes!
        PlanePreset preset = GameData.planePresets.get(0);
        Plane player = PlaneFactory.build(preset, new PlayerController());
        Events.emit(new PlayerSpawnEvent(player));

        //Creae a dummy plane
        Plane dummy = PlaneFactory.build(preset);
        Events.emit(new PlaneSpawnEvent(dummy));

    }
}
