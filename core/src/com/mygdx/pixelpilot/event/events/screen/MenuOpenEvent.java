package com.mygdx.pixelpilot.event.events.screen;

import com.mygdx.pixelpilot.event.events.GameEvent;
import com.mygdx.pixelpilot.screen.menu.Menu;

public class MenuOpenEvent extends GameEvent {

    private Menu menu;

    public MenuOpenEvent(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

}
