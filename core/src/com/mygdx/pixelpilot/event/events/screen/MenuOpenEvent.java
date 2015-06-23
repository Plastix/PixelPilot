package com.mygdx.pixelpilot.event.events.screen;

import com.mygdx.pixelpilot.event.events.GameEvent;
import com.mygdx.pixelpilot.screen.DependentBuilder;
import com.mygdx.pixelpilot.screen.menu.Menu;

public class MenuOpenEvent extends GameEvent {

    private DependentBuilder<? extends Menu> menu;

    public MenuOpenEvent(DependentBuilder<? extends Menu> menu) {
        this.menu = menu;
    }

    public DependentBuilder<? extends Menu> getMenu() {
        return menu;
    }

}
