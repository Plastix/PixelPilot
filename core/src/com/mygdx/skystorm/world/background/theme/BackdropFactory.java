package com.mygdx.skystorm.world.background.theme;


import com.badlogic.gdx.Gdx;
import com.mygdx.skystorm.world.background.Backdrop;

public class BackdropFactory {

    public static enum ThemePreset {
        ISLANDS(IslandTheme.class);

        public Class<? extends BackdropTheme> themeClass;

        ThemePreset(Class<? extends BackdropTheme> themeClass) {
            this.themeClass = themeClass;
        }
    }

    public static BackdropTheme buildTheme(ThemePreset presetTheme){
        try {
            return presetTheme.themeClass.newInstance();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Exception building BackdropTheme " + presetTheme.themeClass.getSimpleName());
        }
    }

    public static Backdrop buildBackdrop(int width, int height, int scale, BackdropTheme theme) {
        float scaleX = Gdx.graphics.getWidth() / 960;
        float scaleY = Gdx.graphics.getHeight() / 540;
        return new Backdrop((int)(width/(scale * scaleX)), (int)(height/(scale * scaleY)), scale * scaleX, theme);
    }

}
