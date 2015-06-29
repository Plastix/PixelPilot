package com.mygdx.pixelpilot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.game.menu.MainMenu;
import com.mygdx.pixelpilot.game.menu.MenuScreen;

public class PixelPilot extends Game {

    FPSLogger log;

    @Override
    public void create() {
        this.setScreen(new ScreenManager());
        DependentBuilder<? extends Screen> dependentBuilder =
                new MenuScreen.Builder().setMenu(new MainMenu.Builder());
        Events.getBus().publish(new ScreenChangeEvent(dependentBuilder));

        log = new FPSLogger();
    }

    @Override
    public void render() {
        super.render();
        log.log();
    }

}