package com.mygdx.pixelpilot.effect.background.theme;


import com.badlogic.gdx.utils.Scaling;
import com.mygdx.pixelpilot.effect.background.Backdrop;

public class BackdropFactory {

    public enum ThemePreset {
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
        Backdrop backdrop = new Backdrop(width / scale, height / scale, theme);
        backdrop.setFillParent(true);
        backdrop.setScaling(Scaling.fill);
        return backdrop;
    }

}
