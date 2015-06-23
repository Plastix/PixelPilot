package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.loader.*;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.plane.PlanePreset;
import com.mygdx.pixelpilot.plane.armaments.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponFactory;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;

public class CommonAssets extends AssetPack {

    @Override
    public void beforeLoading() {
        ControllerFactory.registerControllers();
        ProjectileFactory.registerProjectiles();
        WeaponFactory.registerWeapons();
    }

    @Override
    public void queueAssets() {
        /* Images */
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
        add(new Asset(Assets.Fonts.options_menu, BitmapFont.class, new FreetypeYamlLoader.FreeTypeYamlLoaderParam(Assets.TTFs.pixel)));

        /* Data */
        add(new Asset(Assets.Data.weapons, WeaponDefinitionLoader.WeaponDefinitionContainer.class));
        add(new Asset(Assets.Data.planes, PlaneDefinitionLoader.PlaneDefinitionContainer.class));
        add(new Asset(Assets.Data.presets, PlanePresetLoader.PlanePresetContainer.class));
        add(new Asset(Assets.Data.levels, LevelLoader.LevelContainer.class));

        /* Backdrop */
        add(new Asset("menu_backdrop", Backdrop.class, new BackdropLoader.BackdropParam(Config.NativeView.width,Config.NativeView.height)));
    }

    @Override
    public void afterLoading() {
        for (PlanePreset o : GameData.planePresets) {
            o.resolveWeaponSlotLinkages();
        }
    }
}
