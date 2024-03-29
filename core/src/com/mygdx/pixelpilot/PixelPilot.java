package com.mygdx.pixelpilot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.ResizeEvent;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.game.campaign.CampaignGameScreen;
import com.mygdx.pixelpilot.game.menu.MainMenu;
import com.mygdx.pixelpilot.game.menu.MenuScreen;

public class PixelPilot extends Game {

    FPSLogger log;

    @Override
    public void create() {
        this.setScreen(new ScreenManager());
//        DependentBuilder<? extends Screen> dependentBuilder =
//                new MenuScreen.Builder().setMenu(new MainMenu.Builder());
//        Events.getBus().publish(new ScreenChangeEvent(dependentBuilder));

        Events.getBus().publish(new ScreenChangeEvent(new CampaignGameScreen.Loader()));

        log = new FPSLogger();
    }

    @Override
    public void render() {
        super.render();
        log.log();
    }

    @Override
    public void resize(int width, int height) {
        Events.getBus().publish(new ResizeEvent(width, height));
    }
}