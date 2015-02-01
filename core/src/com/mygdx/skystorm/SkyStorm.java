package com.mygdx.skystorm;

import com.badlogic.gdx.Game;
import com.mygdx.skystorm.Data.GameData;
import com.mygdx.skystorm.Data.XMLParser;
import com.mygdx.skystorm.Plane.PlanePreset;
import com.mygdx.skystorm.Screens.MainMenuScreen;

public class SkyStorm extends Game {

    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

}