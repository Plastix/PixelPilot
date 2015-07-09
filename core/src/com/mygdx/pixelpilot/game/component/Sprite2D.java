package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.pixelpilot.data.Assets;

public class Sprite2D extends Component {

    public Sprite sprite;

    public Sprite2D() {
    }

    public Sprite2D(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite2D(Texture texture) {
        this(new Sprite(texture));
    }

    public Sprite2D(String path) {
        this(Assets.manager.get(path, Texture.class));
    }

    public void setPath(String path) {
        sprite = new Sprite(Assets.manager.get(path, Texture.class));
    }
}
