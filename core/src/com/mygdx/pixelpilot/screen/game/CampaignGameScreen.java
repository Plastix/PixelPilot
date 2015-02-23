package com.mygdx.pixelpilot.screen.game;

import com.mygdx.pixelpilot.PixelPilot;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.event.*;
import com.mygdx.pixelpilot.event.events.PlaneSpawnEvent;
import com.mygdx.pixelpilot.event.events.player.PlayerSpawnEvent;
import com.mygdx.pixelpilot.plane.*;
import com.mygdx.pixelpilot.plane.controller.PlayerController;
import com.mygdx.pixelpilot.screen.game.hud.HUD;

public class CampaignGameScreen extends GameScreen implements Listener {

    private World world;
    private HUD hud;

    public CampaignGameScreen(PixelPilot game) {
        super(game);
        hud = new HUD();
        world = new World(3000, 3000);

        addStage(world);
        addStage(hud);

        // let's spawn some planes!
        PlanePreset preset = GameData.planePresets.get(0);
        Plane player = PlaneFactory.build(preset, new PlayerController());
        Events.emit(new PlayerSpawnEvent(player), this);

        //Creae a dummy plane
        Plane dummy = PlaneFactory.build(preset);
        Events.emit(new PlaneSpawnEvent(dummy), this);

    }
}
