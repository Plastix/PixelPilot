package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ShadowComponent extends Component {

    public int offsetX;
    public int offsetY;
    public float scaleX;
    public float scaleY;
    public Color shadowColor;
    public Sprite sprite;

    public ShadowComponent(int offsetX, int offsetY, float scaleX, float scaleY, Color shadowColor) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.shadowColor = shadowColor;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
