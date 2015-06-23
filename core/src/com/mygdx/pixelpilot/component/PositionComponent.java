package com.mygdx.pixelpilot.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PositionComponent extends Component implements Pool.Poolable {
    public float x = 0.0f;
    public float y = 0.0f;

    @Override
    public void reset() {
        x = 0.0f;
        y = 0.0f;
    }
}
