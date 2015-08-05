package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Tolerance extends Component {
    public float min, max;

    public Tolerance(int min, int max) {
        this.max=max;
        this.min=min;
    }
}
