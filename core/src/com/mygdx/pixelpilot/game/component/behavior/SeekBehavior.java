package com.mygdx.pixelpilot.game.component.behavior;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.plane.SteerableActor;

public class SeekBehavior extends Behavior {
    public Steerable<Vector2> target;
    public SeekBehavior(final Vector2 point) {
        target = new SteerableAdapter<Vector2>(){
            @Override
            public Vector2 getPosition() {
                return point;
            }
        };
    }
}
