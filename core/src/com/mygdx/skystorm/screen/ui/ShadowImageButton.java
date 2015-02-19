package com.mygdx.skystorm.screen.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Scaling;

public class ShadowImageButton extends ImageButton {


    public ShadowImageButton(ImageButtonStyle style) {
        super(style);

        Cell cell = getCell(getImage());
        ShadowImage shadowImage = new ShadowImage();
        shadowImage.setDrawable(style.imageUp);
        shadowImage.setScaling(Scaling.fit);
        shadowImage.setFillParent(true);
        cell.setActor(shadowImage);
        cell.expand().fill();
    }
}
