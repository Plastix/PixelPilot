package com.mygdx.pixelpilot.event.events.screen;


import com.badlogic.gdx.Screen;
import com.mygdx.pixelpilot.event.events.GameEvent;

public class ScreenChangeEvent extends GameEvent {

    private Screen newScreen;

    public ScreenChangeEvent(Screen newScreen) {
        this.newScreen = newScreen;
    }

    public Screen getNewScreen() {
        return newScreen;
    }
}
