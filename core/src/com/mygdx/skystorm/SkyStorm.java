package com.mygdx.skystorm;

import com.badlogic.gdx.Game;
import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.data.YamlParser;
import com.mygdx.skystorm.screen.MainMenuScreen;

public class SkyStorm extends Game {

    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
        YamlParser.loadAllData();
    }

    @Override
    public void render() {
        super.render();
    }

}