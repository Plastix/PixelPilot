package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.loader.*;
import com.mygdx.pixelpilot.game.plane.PlanePreset;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponFactory;
import com.mygdx.pixelpilot.game.plane.controller.ControllerFactory;

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
        add(new Asset(Assets.Images.pixel, Texture.class));
        add(new Asset(Assets.Images.plane_marker, Texture.class));

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
    }

    @Override
    public void afterLoading() {
        for (PlanePreset o : GameData.planePresets) {
            o.resolveWeaponSlotLinkages();
        }
    }
}
