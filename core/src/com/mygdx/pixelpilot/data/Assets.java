package com.mygdx.pixelpilot.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;

public class Assets {

    //TODO Asset management!
    public static AssetManager manager = new AssetManager();

    public static void queueAssets(){
    }

    public static boolean update(){
        return manager.update();
    }

    public static class image {

        public static final String menu_logo = "image/menu_logo.png";
        public static final String pixel = "image/pixel.png";
        public static final String wrench = "image/wrench_icon.png";
        public static final String settings = "image/wrench_icon.png";
        public static final String plane = "image/plane_icon.png";
        public static final String trophy = "image/trophy_icon.png";
        public static final String pause_button = "image/pause_button.png";
        public static final String plane_marker = "image/plane_marker.png";
        public static final String play_icon = "image/play_icon.png";
        public static final String restart_icon = "image/restart_icon.png";
        public static final String menu_icon = "image/menu_icon.png";

    }

    public static class font {

        public static final String pixel = "font/8-Bit-Madness.ttf";
    }

    public static class data {

        public static final String weapons = "data/Weapons.yml";
        public static final String planes = "data/PlaneDefinitions.yml";
        public static final String presets = "data/PlanePresets.yml";
        public static final String levels = "data/Levels.yml";

    }
}

