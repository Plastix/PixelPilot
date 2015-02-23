package com.mygdx.pixelpilot.screen.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ShadowLabel extends Label {

    private ShadowLabelStyle style;

    public ShadowLabel(CharSequence text, ShadowLabelStyle style) {
        super(text, style);
        this.style = style;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();
        Color shadowColor = style.shadowColor;
        shadowColor.a *= parentAlpha;
        if (getStyle().background != null) {
            batch.setColor(shadowColor.r, shadowColor.g, shadowColor.b, shadowColor.a);
            getStyle().background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        getBitmapFontCache().tint(shadowColor);
        getBitmapFontCache().setPosition(getX(), getY() - style.shadowDepth);
        getBitmapFontCache().draw(batch);
        super.draw(batch, parentAlpha);
    }

    public static class ShadowLabelStyle extends LabelStyle {

        public Color shadowColor;
        public int shadowDepth;

    }


}
