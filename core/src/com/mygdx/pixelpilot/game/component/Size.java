package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Size extends Component {

    public float scaleX;
    public float scaleY;

    public Size(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public Size() {
        this.scaleX = 1;
        this.scaleY = 1;
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + scaleX +
                ", height=" + scaleY +
                '}';
    }
}
