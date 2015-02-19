package com.mygdx.skystorm.screen.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ShadowLabel extends Label {

    private Color shadowColor;

    public ShadowLabel(CharSequence text, LabelStyle style) {
        super(text, style);
        generateShadowColor(style.fontColor);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();
        shadowColor.a *= parentAlpha;
        if (getStyle().background != null) {
            batch.setColor(shadowColor.r, shadowColor.g, shadowColor.b, shadowColor.a);
            getStyle().background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        getBitmapFontCache().tint(shadowColor);
        getBitmapFontCache().setPosition(getX(), getY() - 8);
        getBitmapFontCache().draw(batch);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void setStyle(LabelStyle style) {
        super.setStyle(style);
        generateShadowColor(style.fontColor);
    }

    private void generateShadowColor(Color color){
        //Copy color so we don't change the original
        Color dark = new Color(color).mul(0.5f);
        dark.a = 1f;

        shadowColor = dark;
    }


}
