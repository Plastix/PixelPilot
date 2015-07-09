package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Rotation extends Component {

    public float rotation;

    public Rotation() {
        this.rotation = 0;
    }

    public Rotation(float rotation) {
        this.rotation = rotation;
    }

    public Rotation set(float rotation) {
        this.rotation = rotation;
        return this;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "rotation=" + rotation +
                '}';
    }
}
