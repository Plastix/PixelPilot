package com.mygdx.pixelpilot.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.events.screen.MenuCloseEvent;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.screen.menu.Menu;

import java.util.Stack;

public class ScreenManager implements Screen, Listener {

    private Screen currentScreen;
    private final Stack<Menu> menuStack;

    public ScreenManager() {
        this.menuStack = new Stack<Menu>();
        Events.register(this);

        this.currentScreen = new SplashScreen();
//        Events.emit(new MenuOpenEvent(new MainMenu()), this);
    }

    @EventHandler
    public void onScreenChange(ScreenChangeEvent event){
        currentScreen.dispose();
        for(Menu menu : menuStack){
            menu.dispose();
        }
        menuStack.clear();
        this.currentScreen = event.getNewScreen();
    }

    @EventHandler
    public void onMenuOpen(MenuOpenEvent event){
        this.menuStack.push(event.getMenu());
        Gdx.input.setInputProcessor(event.getMenu());
    }

    @EventHandler
    public void onMenuClose(MenuCloseEvent event){
        Menu closed = menuStack.pop();
        closed.dispose();
        if(this.menuStack.size() != 0){
            Gdx.input.setInputProcessor(menuStack.peek());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        currentScreen.render(delta);
        if(menuStack.size() != 0){
            menuStack.peek().draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        currentScreen.resize(width, height);
        if(menuStack.size() != 0) {
            menuStack.peek().getViewport().update(width, height, true);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        currentScreen.dispose();
        menuStack.clear();
    }
}
