package com.mygdx.pixelpilot.effect.background.theme;

import com.badlogic.gdx.graphics.Pixmap;

public interface BackdropTheme {

    public void colorize(Pixmap pixmap, float height);
    public float[][] getNoise(int width, int height);
}
