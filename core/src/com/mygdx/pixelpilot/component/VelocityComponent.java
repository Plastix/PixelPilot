package com.mygdx.pixelpilot.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class VelocityComponent extends Component implements Pool.Poolable {
    public float vx = 0.0f;
    public float vy = 0.0f;

    @Override
    public void reset() {
        vx = 0.0f;
        vy = 0.0f;
    }
}
