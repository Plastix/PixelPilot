package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.data.loader.*;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;

public class MenuScreenAssets extends AssetPack {
    @Override
    public void queueAssets() {
        add(new Asset("menu_backdrop", Backdrop.class,
                new BackdropLoader.BackdropParam(Config.NativeView.width,Config.NativeView.height, BackdropFactory.ThemePreset.ISLANDS)));
    }
}
   