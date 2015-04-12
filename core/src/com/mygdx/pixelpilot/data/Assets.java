package com.mygdx.pixelpilot.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {

    //TODO Asset management!
    public static AssetManager manager;

    public static void initializeManager() {
        manager = new AssetManager();

        //Set loaders for freetype fonts and generators
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        manager.setLoader(BitmapFont.class, new FreetypeFontLoader(new InternalFileHandleResolver()));
    }

    public static void queueAssets() {
        queueFonts();
        //Load other assets here
    }

    //TODO Is it worth it to define and load them from Yaml?
    //We should probably design our game to use as few font types and sizes as possible
    private static void queueFonts(){
        //Large yellow font with darker yellow shadow
        FreeTypeFontGenerator.FreeTypeFontParameter playFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        playFont.size = 250;
        playFont.color = new Color(0.9f, 0.92f, 0.36f, 1);
        playFont.shadowColor = new Color(0.72f, 0.74f, 0.3f, 1);
        playFont.shadowOffsetY = 8;
        loadFont("play-font", playFont);

        //Small white font with black shadow
        FreeTypeFontGenerator.FreeTypeFontParameter hudFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        hudFont.size = 76;
        hudFont.color = new Color(1, 1, 1, 1);
        hudFont.shadowColor = new Color(0, 0, 0, 1);
        hudFont.shadowOffsetY = 5;
        loadFont("hud-font", hudFont);

        FreeTypeFontGenerator.FreeTypeFontParameter waveFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        waveFont.size = 100;
        waveFont.color = new Color(1, 1, 1, 1);
        waveFont.shadowColor = new Color(0, 0, 0, 1);
        waveFont.shadowOffsetY = 7;
        loadFont("wave-font", waveFont);

        //Large red font with darker red shadow
        FreeTypeFontGenerator.FreeTypeFontParameter logoFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        logoFont.size = 150;
        logoFont.color = new Color(0.75f, 0.24f, 0.25f, 1);
        logoFont.shadowColor = new Color(0.44f, 0.04f, 0.04f, 1);
        logoFont.shadowOffsetY = 7;
        loadFont("logo-font", logoFont);

        //Generic label testing font
        FreeTypeFontGenerator.FreeTypeFontParameter labelFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        labelFont.size = 150;
        labelFont.color = new Color(1, 1, 1, 1);
        labelFont.shadowColor = new Color(0, 0, 0, 1);
        labelFont.shadowOffsetY = 7;
        loadFont("label-font", labelFont);
    }

    private static void loadFont(String name, FreeTypeFontGenerator.FreeTypeFontParameter freeTypeParam) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter loaderParam = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        loaderParam.fontFileName = font.pixel;
        loaderParam.fontParameters = freeTypeParam;
        manager.load(name, BitmapFont.class, loaderParam);

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

