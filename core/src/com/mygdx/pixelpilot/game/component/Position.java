package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Position extends Component {

    public float x;
    public float y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(float _x, float _y) {
        x += _x;
        y += _y;
    }

}
