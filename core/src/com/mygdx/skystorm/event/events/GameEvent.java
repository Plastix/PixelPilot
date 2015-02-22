package com.mygdx.skystorm.event.events;

abstract public class GameEvent {
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    private Object source;

}
