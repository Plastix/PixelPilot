package com.mygdx.pixelpilot;

import com.badlogic.gdx.Game;
import com.mygdx.pixelpilot.data.YamlParser;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;
import com.mygdx.pixelpilot.screen.game.CampaignGameScreen;

public class PixelPilot extends Game {

    @Override
    public void create() {
        ControllerFactory.registerControllers();
        YamlParser.loadAllData();
        this.setScreen(new CampaignGameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

}