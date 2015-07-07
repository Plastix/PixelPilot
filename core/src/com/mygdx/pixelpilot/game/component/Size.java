package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Size extends Component {

    public float width;
    public float height;
    public Size(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Size() {
        this.width = 1;
        this.height = 1;
    }
}
