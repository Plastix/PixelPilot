package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Weight extends Component {

    public int weight;

    public Weight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weight=" + weight +
                '}';
    }
}
