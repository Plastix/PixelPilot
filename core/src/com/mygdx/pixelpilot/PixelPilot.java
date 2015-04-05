package com.mygdx.pixelpilot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.pixelpilot.screen.ScreenManager;
import com.mygdx.pixelpilot.data.YamlParser;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;
import com.mygdx.pixelpilot.plane.shooty.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.WeaponFactory;
import com.mygdx.pixelpilot.screen.game.CampaignGameScreen;

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
        log.log();
    }

}