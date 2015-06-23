package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.loader.*;

public class MenuAssets extends AssetPack {
    @Override
    public boolean update() {
        return true;
    }

    @Override
    public void queueAssets() {
        add(new Asset(Assets.Images.menu_logo, Texture.class));
        add(new Asset(Assets.Images.pixel, Texture.class));
        add(new Asset(Assets.Images.wrench, Texture.class));
        add(new Asset(Assets.Images.plane, Texture.class));
        add(new Asset(Assets.Images.trophy, Texture.class));
        add(new Asset(Assets.Images.pause_button, Texture.class));
        add(new Asset(Assets.Images.plane_marker, Texture.class));
        add(new Asset(Assets.Images.play_icon, Texture.class));
        add(new Asset(Assets.Images.restart_icon, Texture.class));
        add(new Asset(Assets.Images.menu_icon, Texture.class));

        /* .ttf */
        add(new Asset(Assets.TTFs.pixel, FreeTypeFontGenerator.class));

        /* Fonts */
        add(new Asset(Assets.Fonts.logo, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(Assets.TTFs.pixel)));
        add(new Asset(Assets.Fonts.play_button, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(Assets.TTFs.pixel)));
        add(new Asset(Assets.Fonts.wave_spawn, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(Assets.TTFs.pixel)));
        add(new Asset(Assets.Fonts.hud_text, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(Assets.TTFs.pixel)));

        /* Data */
        add(new Asset(Assets.Data.weapons, WeaponDefinitionLoader.WeaponDefinitionContainer.class));
        add(new Asset(Assets.Data.planes, PlaneDefinitionLoader.PlaneDefinitionContainer.class));
        add(new Asset(Assets.Data.presets, PlanePresetLoader.PlanePresetContainer.class));
        add(new Asset(Assets.Data.levels, LevelLoader.LevelContainer.class));

    }
}
