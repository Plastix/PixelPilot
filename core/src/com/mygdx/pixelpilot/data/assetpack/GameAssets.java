package com.mygdx.pixelpilot.data.assetpack;

import com.mygdx.pixelpilot.data.loader.BackdropLoader;
import com.mygdx.pixelpilot.effect.background.Backdrop;

public class GameAssets extends AssetPack {
    @Override
    public void queueAssets() {
        add(new Asset("backdrop", Backdrop.class, new BackdropLoader.BackdropParam(3000,3000)));
    }
}
