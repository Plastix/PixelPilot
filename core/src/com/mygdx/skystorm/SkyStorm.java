package com.mygdx.skystorm;

import com.badlogic.gdx.Game;
import com.mygdx.skystorm.data.YamlParser;
import com.mygdx.skystorm.plane.controller.ControllerFactory;
import com.mygdx.skystorm.screen.game.CampaignGameScreen;

public class SkyStorm extends Game {

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