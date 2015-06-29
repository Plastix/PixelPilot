package com.mygdx.pixelpilot.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

public class ShadowImageButton extends ImageButton {

    private ShadowImage shadowImage;

    public ShadowImageButton(ShadowImageButtonStyle style) {
        super(style);

        Cell cell = getCell(getImage());

        ShadowImage.ShadowImageStyle imageStyle = new ShadowImage.ShadowImageStyle();
        imageStyle.shadowDepth = style.shadowDepth;
        imageStyle.shadowColor = style.shadowColor;

        shadowImage = new ShadowImage(imageStyle);
        shadowImage.setDrawable(style.imageUp);
        shadowImage.setScaling(Scaling.fit);
        shadowImage.setFillParent(true);
        cell.setActor(shadowImage);
        cell.expand().fill();
        setSize(getPrefWidth(), getPrefHeight());
    }

    private void updateImage () {
        Drawable drawable = null;
        if (isDisabled() && getStyle().imageDisabled != null)
            drawable = getStyle().imageDisabled;
        else if (isPressed() && getStyle().imageDown != null)
            drawable = getStyle().imageDown;
        else if (isChecked() && getStyle().imageChecked != null)
            drawable = (getStyle().imageCheckedOver != null && isOver()) ? getStyle().imageCheckedOver : getStyle().imageChecked;
        else if (isOver() && getStyle().imageOver != null)
            drawable = getStyle().imageOver;
        else if (getStyle().imageUp != null) //
            drawable = getStyle().imageUp;
        shadowImage.setDrawable(drawable);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        updateImage();
        super.draw(batch, parentAlpha);
    }

    public static class ShadowImageButtonStyle extends ImageButtonStyle {

        public Color shadowColor;
        public int shadowDepth;
    }
}
