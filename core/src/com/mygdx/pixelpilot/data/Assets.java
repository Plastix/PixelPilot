package com.mygdx.pixelpilot.data;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.mygdx.pixelpilot.data.assetpack.AssetPack;
import com.mygdx.pixelpilot.data.loader.*;
import com.mygdx.pixelpilot.effect.background.Backdrop;

import java.util.ArrayList;
import java.util.List;

public class Assets {

    public static AssetManager manager = new AssetManager() {
        {
            // whole lotta loadahs

            InternalFileHandleResolver resolver = new InternalFileHandleResolver();

            setLoader(FreeTypeFontGenerator.class,
                    new FreeTypeFontGeneratorLoader(resolver));

            setLoader(WeaponDefinitionLoader.WeaponDefinitionContainer.class,
                    new WeaponDefinitionLoader(resolver));

            setLoader(PlaneDefinitionLoader.PlaneDefinitionContainer.class,
                    new PlaneDefinitionLoader(resolver));

            setLoader(PlanePresetLoader.PlanePresetContainer.class,
                    new PlanePresetLoader(resolver));

            setLoader(LevelLoader.LevelContainer.class,
                    new LevelLoader(resolver));

            setLoader(BitmapFont.class, ".yml",
                    new FreetypeYamlLoader(resolver));

            setLoader(Backdrop.class,
                    new BackdropLoader(resolver));

        }
    };

    public static void queueAssets() {

        for (ResourceContainer c : resources.resources) {
            manager.load(c.path, c.type, c.param);
        }
    }

    public static boolean update() {
        return manager.update();
    }

    public abstract static class Images {
        public static final String menu_logo = "image/menu_logo.png";
        public static final String pixel = "image/pixel.png";
        public static final String wrench = "image/wrench_icon.png";
        public static final String plane = "image/plane_icon.png";
        public static final String trophy = "image/trophy_icon.png";
        public static final String pause_button = "image/pause_button.png";
        public static final String plane_marker = "image/plane_marker.png";
        public static final String play_icon = "image/play_icon.png";
        public static final String restart_icon = "image/restart_icon.png";
        public static final String menu_icon = "image/menu_icon.png";
    }

    public abstract static class TTFs {
        public static final String pixel = "font/8-Bit-Madness.ttf";
    }

    public abstract static class Fonts {
        public static final String logo = "data/font_face/logo.yml";
        public static final String play_button = "data/font_face/play_button.yml";
        public static final String wave_spawn = "data/font_face/wave_spawn.yml";
        public static final String hud_text = "data/font_face/HUD_text.yml";
        public static final String options_menu = "data/font_face/options_menu.yml";
    }

    public abstract static class Data {
        public static final String weapons = "data/Weapons.yml";
        public static final String planes = "data/PlaneDefinitions.yml";
        public static final String presets = "data/PlanePresets.yml";
        public static final String levels = "data/Levels.yml";
    }

    static class ResourceContainer {
        String path;
        Class type;
        AssetLoaderParameters param;

        public ResourceContainer(String path, Class type) {
            this.path = path;
            this.type = type;
        }

        public ResourceContainer(String path, Class type, AssetLoaderParameters param) {
            this(path, type);
            this.param = param;
        }
    }

    public static class resources {
        public static final List<ResourceContainer> resources = new ArrayList<ResourceContainer>() {
            {
//                /* Images */
//                add(new ResourceContainer(Images.menu_logo, Texture.class));
//                add(new ResourceContainer(Images.pixel, Texture.class));
//                add(new ResourceContainer(Images.wrench, Texture.class));
//                add(new ResourceContainer(Images.plane, Texture.class));
//                add(new ResourceContainer(Images.trophy, Texture.class));
//                add(new ResourceContainer(Images.pause_button, Texture.class));
//                add(new ResourceContainer(Images.plane_marker, Texture.class));
//                add(new ResourceContainer(Images.play_icon, Texture.class));
//                add(new ResourceContainer(Images.restart_icon, Texture.class));
//                add(new ResourceContainer(Images.menu_icon, Texture.class));
//
//                /* .ttf */
//                add(new ResourceContainer(TTFs.pixel, FreeTypeFontGenerator.class));
//
//                /* Fonts */
//                add(new ResourceContainer(Fonts.logo, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(TTFs.pixel)));
//                add(new ResourceContainer(Fonts.play_button, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(TTFs.pixel)));
//                add(new ResourceContainer(Fonts.wave_spawn, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(TTFs.pixel)));
//                add(new ResourceContainer(Fonts.hud_text, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(TTFs.pixel)));
//
//                /* Data */
//                add(new ResourceContainer(Data.weapons, WeaponDefinitionLoader.WeaponDefinitionContainer.class));
//                add(new ResourceContainer(Data.planes, PlaneDefinitionLoader.PlaneDefinitionContainer.class));
//                add(new ResourceContainer(Data.presets, PlanePresetLoader.PlanePresetContainer.class));
//                add(new ResourceContainer(Data.levels, LevelLoader.LevelContainer.class));
            }
        };
    }
}

