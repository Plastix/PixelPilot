package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.pixelpilot.data.Assets;

public class PauseMenuAssets extends AssetPack {
    @Override
    public void queueAssets() {
        add(new Asset(Assets.Images.wrench, Texture.class));
        add(new Asset(Assets.Images.play_icon, Texture.class));
        add(new Asset(Assets.Images.restart_icon, Texture.class));
        add(new Asset(Assets.Images.menu_icon, Texture.class));

    }
}
