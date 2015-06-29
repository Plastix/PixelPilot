package com.mygdx.pixelpilot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.screen.ScreenManager;
import com.mygdx.pixelpilot.screen.menu.MainMenu;
import com.mygdx.pixelpilot.screen.menu.MenuScreen;

public class PixelPilot extends Game  {

    FPSLogger log;

    @Override
    public void create() {

//        AssetPack[] assetPacks = {new CommonAssets(), new MenuAssets()};
//        MenuScreen nextScreen = new MenuScreen();
//        BarLoadingScreen barLoader = new BarLoadingScreen();
//        SplashScreen splashScreen = new SplashScreen(assetPacks, barLoader, nextScreen);

        this.setScreen(new ScreenManager());
        Events.getBus().publish(new ScreenChangeEvent(new MenuScreen.Builder().setMenu(new MainMenu.Builder())));

        log = new FPSLogger();
    }

    @Override
    public void render() {
        super.render();
//        log.log();
    }

}