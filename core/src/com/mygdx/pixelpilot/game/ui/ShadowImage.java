package com.mygdx.pixelpilot.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public class ShadowImage extends Image {

    private ShadowImageStyle style;

    public ShadowImage(ShadowImageStyle style) {
        this.style = style;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();

        Color color = style.shadowColor;
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        float x = getX();
        float y = getY() - style.shadowDepth;
        float scaleX = getScaleX();
        float scaleY = getScaleY();

        if (getDrawable() instanceof TransformDrawable) {
            float rotation = getRotation();
            if (scaleX != 1 || scaleY != 1 || rotation != 0) {
                ((TransformDrawable)getDrawable()).draw(batch, x + getImageX(), y + getImageY(), getOriginX() - getImageX(), getOriginY() - getImageY(),
                        getImageWidth(), getImageHeight(), scaleX, scaleY, rotation);
                return;
            }
        }
        if (getDrawable() != null) getDrawable().draw(batch, x + getImageX(), y + getImageY(), getImageWidth() * scaleX, getImageHeight() * scaleY);
        super.draw(batch, parentAlpha);
    }

    public static class ShadowImageStyle {

        public Color shadowColor;
        public int shadowDepth;

    }
}
