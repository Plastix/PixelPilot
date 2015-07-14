package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Turning extends Component {
    public float turnAmount;
    public float minTurnRadius;

    // adds the given amount to this frame's turnAmount.
    public void turn(float amount) {
        // can't think why this should exceed 1
        // (turnAmount = 1 is the max angle change in a single frame such
        //  that the entity does not turn in a tighter circle than
        //  specified by minTurnRadius)
        turnAmount = Math.min(turnAmount + amount, 1);
    }

    // should be called after the system has turned the entity
    public void reset() {
        turnAmount = 0;
    }
}
