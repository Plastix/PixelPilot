package com.mygdx.pixelpilot.data.assetpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.loader.BackdropLoader;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;

public class GameAssets extends AssetPack {
    @Override
    public void queueAssets() {
        add(new Asset("backdrop", Backdrop.class,
                new BackdropLoader.BackdropParam(3000, 3000, BackdropFactory.ThemePreset.ISLANDS))); // todo: size adjustable

        ParticleEffectLoader.ParticleEffectParameter particleParam = new ParticleEffectLoader.ParticleEffectParameter();
        particleParam.imagesDir = Gdx.files.internal(Assets.Data.image_dir);
        add(new Asset(Assets.Data.smoke, ParticleEffect.class, particleParam));
    }
}
