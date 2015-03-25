package com.mygdx.pixelpilot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.pixelpilot.data.YamlParser;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;
import com.mygdx.pixelpilot.screen.game.CampaignGameScreen;
import com.mygdx.pixelpilot.screen.menu.MainMenuScreen;

public class PixelPilot extends Game {
    FPSLogger log;
    @Override
    public void create() {
        ControllerFactory.registerControllers();
        YamlParser.loadAllData();
        this.setScreen(new CampaignGameScreen(this));
        log = new FPSLogger();
    }

    @Override
    public void render() {
        super.render();
//        log.log();
    }

}