package com.mygdx.skystorm;

import com.badlogic.gdx.Game;
import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.data.XMLParser;
import com.mygdx.skystorm.screen.MainMenuScreen;

public class SkyStorm extends Game {

    @Override
    public void create() {
        XMLParser.parsePlanePresets();
        System.out.println(GameData.planePresets);
        this.setScreen(new MainMenuScreen(this));

    }

    @Override
    public void render() {
        super.render();
    }

}