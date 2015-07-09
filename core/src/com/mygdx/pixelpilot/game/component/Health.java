package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Health extends Component {

    public float health;

    public Health() {
        this(1);
    }

    public Health(float health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Health{" +
                "health=" + health +
                '}';
    }
}
