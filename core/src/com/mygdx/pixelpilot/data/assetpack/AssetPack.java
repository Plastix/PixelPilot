package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.mygdx.pixelpilot.data.Assets;

import java.util.ArrayList;
import java.util.List;

public abstract class AssetPack {
    protected final List<Asset> assets;

    public AssetPack() {
        assets = new ArrayList<Asset>();
        beforeLoading();
    }

    public static AssetPack fromList(final ArrayList<Asset> list) {
        return new AssetPack() {
            @Override
            public void queueAssets() {
                for (Asset asset : list) {
                    add(asset);
                }
            }
        };
    }

    public static class Asset {
        public String path;
        public Class type;
        public AssetLoaderParameters param;

        public Asset(String path, Class type) {
            this.path = path;
            this.type = type;
        }

        public Asset(String path, Class type, AssetLoaderParameters param) {
            this(path, type);
            this.param = param;
        }
    }

    protected void add(Asset c) {
        System.out.println(c.path);
        if (!Assets.manager.isLoaded(c.path, c.type)) {
            Assets.manager.load(c.path, c.type, c.param);
        }
    }

    public boolean update() {
        if (Assets.manager.update()) {
            afterLoading();
            return true;
        }
        return false;
    }

    public void beforeLoading() {
    }

    public void afterLoading() {
    }

    public abstract void queueAssets();
}
