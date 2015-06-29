package com.mygdx.pixelpilot.event.events.screen;

import com.mygdx.pixelpilot.event.events.GameEvent;
import com.mygdx.pixelpilot.DependentBuilder;
import com.mygdx.pixelpilot.game.menu.Menu;

public class MenuOpenEvent extends GameEvent {

    private DependentBuilder<? extends Menu> menu;

    public MenuOpenEvent(DependentBuilder<? extends Menu> menu) {
        this.menu = menu;
    }

    public DependentBuilder<? extends Menu> getMenu() {
        return menu;
    }

}
