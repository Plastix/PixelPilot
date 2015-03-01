package com.mygdx.pixelpilot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

import com.mygdx.pixelpilot.screen.ScreenManager;

public class PixelPilot extends Game {

    FPSLogger log;
    @Override
    public void create() {
        this.setScreen(new ScreenManager());
        log = new FPSLogger();
    }

    @Override
    public void render() {
        super.render();
//        log.log();
    }

}