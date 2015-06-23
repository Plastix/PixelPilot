package com.mygdx.pixelpilot.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.MenuCloseEvent;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.screen.loading.animation.BarAnimation;
import com.mygdx.pixelpilot.screen.loading.LoadingScreen;
import com.mygdx.pixelpilot.screen.menu.Menu;
import net.engio.mbassy.listener.Handler;

import java.util.Stack;

public class ScreenManager implements Screen {

    private Screen currentScreen;
    private final Stack<Menu> menuStack;

    public ScreenManager() {
        Events.getBus().subscribe(this);
        this.menuStack = new Stack<Menu>();
    }

    @Handler
    public void onScreenChange(ScreenChangeEvent event) {
        try {
            if (currentScreen != null) {
                currentScreen.dispose();
            }
            for (Menu menu : menuStack) {
                menu.dispose();
            }
            menuStack.clear();

            // todo: check if we should we show a loading screen
            DependentBuilder<? extends Screen> builder = event.getBuilder();
            if (!(currentScreen instanceof LoadingScreen) && !builder.getPacks().isEmpty()) {
                currentScreen = new LoadingScreen(builder.getAnimation(), builder);
            } else {
                currentScreen = builder.build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Handler
    public void onMenuOpen(MenuOpenEvent event) {
        DependentBuilder<? extends Menu> builder = event.getMenu();
        Menu menu = builder.build();

        this.menuStack.push(menu);
        Gdx.input.setInputProcessor(menu);
    }

    @Handler
    public void onMenuClose(MenuCloseEvent event) {
        Menu closed = menuStack.pop();
        closed.dispose();
        if (this.menuStack.size() != 0) {
            Gdx.input.setInputProcessor(menuStack.peek());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (currentScreen == null) return;

        currentScreen.render(delta);
        if (menuStack.size() != 0 ) {
            menuStack.peek().draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (currentScreen == null) return;
        currentScreen.resize(width, height);
        if (menuStack.size() != 0) {
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
