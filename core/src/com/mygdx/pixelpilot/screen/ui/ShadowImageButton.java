package com.mygdx.pixelpilot.screen.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Scaling;

public class ShadowImageButton extends ImageButton {

    public ShadowImageButton(ShadowImageButtonStyle style) {
        super(style);

        Cell cell = getCell(getImage());

        ShadowImage.ShadowImageStyle imageStyle = new ShadowImage.ShadowImageStyle();
        imageStyle.shadowDepth = style.shadowDepth;
        imageStyle.shadowColor = style.shadowColor;

        ShadowImage shadowImage = new ShadowImage(imageStyle);
        shadowImage.setDrawable(style.imageUp);
        shadowImage.setScaling(Scaling.fit);
        shadowImage.setFillParent(true);
        cell.setActor(shadowImage);
        cell.expand().fill();
    }

    public static class ShadowImageButtonStyle extends ImageButtonStyle {

        public Color shadowColor;
        public int shadowDepth;
    }
}
