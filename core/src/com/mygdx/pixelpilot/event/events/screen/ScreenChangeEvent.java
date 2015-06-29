package com.mygdx.pixelpilot.event.events.screen;


import com.badlogic.gdx.Screen;
import com.mygdx.pixelpilot.event.events.GameEvent;
import com.mygdx.pixelpilot.DependentBuilder;

public class ScreenChangeEvent extends GameEvent {

    private DependentBuilder<? extends Screen> builder;

    public ScreenChangeEvent(DependentBuilder<? extends Screen> builder) {
        this.builder = builder;
    }

    public DependentBuilder<? extends Screen> getBuilder() {
        return builder;
    }
}
