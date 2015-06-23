package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.pixelpilot.data.Assets;

public class HUDAssets extends AssetPack {
    @Override
    public void queueAssets() {
        add(new Asset(Assets.Images.plane, Texture.class));
        add(new Asset(Assets.Images.trophy, Texture.class));
        add(new Asset(Assets.Images.pause_button, Texture.class));

    }
}
