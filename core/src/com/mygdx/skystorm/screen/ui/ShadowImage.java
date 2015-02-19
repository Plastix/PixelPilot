package com.mygdx.skystorm.screen.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public class ShadowImage extends Image {

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();

        batch.setColor(0, 0, 0, parentAlpha);

        float x = getX();
        float y = getY() - 8;
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
}
