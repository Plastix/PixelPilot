package com.mygdx.pixelpilot.event.events.screen;

import com.mygdx.pixelpilot.event.events.GameEvent;

public class ResizeEvent extends GameEvent{
    public int width;
    public int height;

    public ResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
