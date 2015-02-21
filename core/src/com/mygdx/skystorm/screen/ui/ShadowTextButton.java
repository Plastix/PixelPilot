package com.mygdx.skystorm.screen.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ShadowTextButton extends TextButton {

    public ShadowTextButton(String text, ShadowTextButtonStyle style) {
        super(text, style);

        Cell cell = getCell(getLabel());

        ShadowLabel.ShadowLabelStyle labelStyle = new ShadowLabel.ShadowLabelStyle();
        labelStyle.shadowColor = style.shadowColor;
        labelStyle.shadowDepth = style.shadowDepth;

        ShadowLabel shadowText = new ShadowLabel(text, labelStyle);
        cell.setActor(shadowText);
    }

    public static class ShadowTextButtonStyle extends TextButtonStyle {

        public Color shadowColor;
        public int shadowDepth;
    }
}
