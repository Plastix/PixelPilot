package com.mygdx.pixelpilot.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class RotationComponent extends Component implements Pool.Poolable{
    public float angle = 0.0f;

    @Override
    public void reset() {
        angle = 0.0f;
    }
}
