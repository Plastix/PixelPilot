package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Projectile extends Component {

    public float lifeSpan;

    public Projectile(float lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    @Override
    public String toString() {
        return "Projectile{" +
                "lifeSpan=" + lifeSpan +
                '}';
    }
}
