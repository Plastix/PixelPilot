package com.mygdx.pixelpilot.screen.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class ShadowTextButton extends TextButton {

    private ShadowLabel shadowLabel;

    public ShadowTextButton(String text, ShadowTextButtonStyle style) {
        super(text, style);

        Label oldLabel = getLabel();
        Cell cell = getCell(oldLabel);
        oldLabel.remove();

        ShadowLabel.ShadowLabelStyle labelStyle = new ShadowLabel.ShadowLabelStyle();
        labelStyle.shadowColor = style.shadowColor;
        labelStyle.shadowDepth = style.shadowDepth;
        labelStyle.fontColor = style.fontColor;
        labelStyle.font = style.font;

        shadowLabel = new ShadowLabel(text, labelStyle);
        cell.setActor(shadowLabel);
    }

    @Override
    public void scaleBy(float scaleX, float scaleY) {
        super.scaleBy(scaleX, scaleY);
        float fontScaleX = shadowLabel.getFontScaleX();
        float fontScaleY = shadowLabel.getFontScaleY();
        shadowLabel.setFontScale(fontScaleX + scaleX, fontScaleY + scaleY);
    }

    public static class ShadowTextButtonStyle extends TextButtonStyle {

        public Color shadowColor;
        public int shadowDepth;
    }
}
