package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.pixelpilot.data.Assets;

public class MainMenuAssets extends AssetPack {
    @Override
    public void queueAssets() {
        add(new Asset(Assets.Images.menu_logo, Texture.class));
        add(new Asset(Assets.Images.wrench, Texture.class));
        add(new Asset(Assets.Images.plane, Texture.class));
    }
}
