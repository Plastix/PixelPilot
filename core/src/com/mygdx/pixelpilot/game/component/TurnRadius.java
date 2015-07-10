package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class TurnRadius extends Component {

    public int turnRadius;

    public TurnRadius() {
        this.turnRadius = 0;
    }

    public TurnRadius(int turnRadius) {
        this.turnRadius = turnRadius;
    }

    @Override
    public String toString() {
        return "TurnRadius{" +
                "turnRadius=" + turnRadius +
                '}';
    }
}
