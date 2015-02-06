package com.mygdx.skystorm.world.background.theme;


public class BackdropFactory {

    public static enum ThemePreset {
        ISLANDS(IslandTheme.class);

        public Class<? extends BackdropTheme> themeClass;

        ThemePreset(Class<? extends BackdropTheme> themeClass) {
            this.themeClass = themeClass;
        }
    }

    public static BackdropTheme build(ThemePreset presetTheme){
        try {
            return presetTheme.themeClass.newInstance();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Exception building BackdropTheme " + presetTheme.themeClass.getSimpleName());
        }
    }

}
