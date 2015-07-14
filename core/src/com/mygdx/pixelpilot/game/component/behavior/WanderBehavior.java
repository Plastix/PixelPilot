package com.mygdx.pixelpilot.game.component.behavior;

import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.plane.SteerableActor;

public class WanderBehavior extends Behavior {
    public Wander<Vector2> wander;

    public WanderBehavior() {
        this.wander = new Wander<Vector2>(new SteerableActor() {

        }).setFaceEnabled(false)
                .setWanderOffset(50)
                .setWanderOrientation(90)
                .setWanderRadius(250)
                .setWanderRate(MathUtils.PI / 8);
    }
}
