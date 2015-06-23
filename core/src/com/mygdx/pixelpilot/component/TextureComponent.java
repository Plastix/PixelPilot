package com.mygdx.pixelpilot.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent extends Component implements Pool.Poolable {
    public TextureRegion region = null;

    @Override
    public void reset() {

    }
}
