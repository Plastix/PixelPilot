package com.mygdx.pixelpilot.screen.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class ShadowLabel extends Label {

    private ShadowLabelStyle style;

    public ShadowLabel(CharSequence text, ShadowLabelStyle style) {
        super(text, style);
        this.style = style;
        setSize(getPrefWidth(), getPrefHeight());
        this.setOrigin(Align.center);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();
        Color color = style.shadowColor;
        color.a *= getColor().a;
        color.a *= parentAlpha;
        if (getStyle().background != null) {
            batch.setColor(color.r, color.g, color.b, color.a);
            getStyle().background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        getBitmapFontCache().tint(color);
        getBitmapFontCache().setPosition(getX(), getY() - style.shadowDepth);
        getBitmapFontCache().draw(batch);
        super.draw(batch, parentAlpha);
    }

    public static class ShadowLabelStyle extends LabelStyle {

        public Color shadowColor;
        public int shadowDepth;

    }


}
