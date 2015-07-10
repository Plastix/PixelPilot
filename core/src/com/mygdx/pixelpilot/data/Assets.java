package com.mygdx.pixelpilot.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.mygdx.pixelpilot.data.loader.*;
import com.mygdx.pixelpilot.effect.background.Backdrop;

/**
 * How to add assets to the game:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * First, add the path to the asset as a public static final String in one of the classes below
 * Second, if the type of the asset does not have a loader associated with it already, create a new loader
 * Register that loader with the AssetManager below
 * Third, add the asset to one or more AssetPacks
 * Finally, ensure that the AssetPack is required in the appropriate DependentBuilder
 */
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

            setLoader(ParticleEffect.class, new ParticleEffectLoader(resolver));

        }
    };

    public static boolean update() {
        return manager.update();
    }

    public abstract static class Images {
        /* General */
        public static final String pixel = "image/pixel.png";

        /* Menu */
        public static final String menu_logo = "image/menu_logo.png";
        public static final String wrench = "image/wrench_icon.png";
        public static final String play_icon = "image/play_icon.png";
        public static final String restart_icon = "image/restart_icon.png";
        public static final String menu_icon = "image/menu_icon.png";
        /* HUD */
        public static final String plane = "image/plane_icon.png";
        public static final String trophy = "image/trophy_icon.png";
        public static final String pause_button = "image/pause_button.png";
        public static final String plane_marker = "image/plane_marker.png";
    }

    public abstract static class TTFs {
        public static final String pixel = "font/8-Bit-Madness.ttf";
    }

    public abstract static class Fonts {
        public static final String font_cache_dir = "generated-fonts/";

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

        public static final String smoke = "data/particle/trail";
        public static final String fire = "data/particle/fire";
        public static final String image_dir = "image";


    }
}

