package com.mygdx.pixelpilot.world.background.theme;

import com.badlogic.gdx.graphics.Pixmap;

public interface BackdropTheme {

    public void colorize(Pixmap pixmap, float height);
    public float[][] getNoise(int width, int height);
}
