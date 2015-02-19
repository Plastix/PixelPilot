package com.mygdx.skystorm.screen.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ShadowTextButton extends TextButton {

    public ShadowTextButton(String text, TextButtonStyle style) {
        super(text, style);

        Cell cell = getCell(getLabel());

        ShadowLabel shadowText = new ShadowLabel(text, new Label.LabelStyle(style.font, style.fontColor));
        cell.setActor(shadowText);
    }
}
