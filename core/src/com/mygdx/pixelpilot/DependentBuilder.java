package com.mygdx.pixelpilot;

import com.mygdx.pixelpilot.data.assetpack.AssetPack;
import com.mygdx.pixelpilot.loading.animation.BarAnimation;
import com.mygdx.pixelpilot.loading.animation.LoadingAnimation;

import java.util.Arrays;
import java.util.Stack;

// todo: name this something that makes sense

/**
 * The loader/builder classes in screens and menus extend this
 * An instance of one of the subclasses is passed to the ScreenChangeEvent or equivalent
 * ScreenManager looks at the list of packs and has the LoadingScreen load them
 * then it calls build to get a new instance of the object (to set as currentScreen/top of menu stack)
 *
 * @param <T> The type of object to build
 */
public abstract class DependentBuilder<T> {
    protected Stack<AssetPack> packs;

    public DependentBuilder(AssetPack... packs) {
        this.packs = new Stack<AssetPack>();
        this.packs.addAll(Arrays.asList(packs));
        for (AssetPack pack : packs) {
            pack.queueAssets();
        }
    }

    public Stack<AssetPack> getPacks() {
        return packs;
    }

    public LoadingAnimation getAnimation() {
        return new BarAnimation();
    }

    public abstract T build();
}
