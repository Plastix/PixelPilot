package com.mygdx.pixelpilot.data.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;

// todo: allow creation of backdrops from any theme. Should be part of the parameter.
public class BackdropLoader extends AsynchronousAssetLoader<Backdrop, BackdropLoader.BackdropParam> {

    public BackdropLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    BackdropTheme theme;

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, BackdropParam parameter) {
         theme = BackdropFactory.buildTheme(BackdropFactory.ThemePreset.ISLANDS);
    }

    @Override
    public Backdrop loadSync(AssetManager manager, String fileName, FileHandle file, BackdropParam parameter) {
        Backdrop backdrop = BackdropFactory.buildBackdrop(parameter.width, parameter.height, 4, theme);
        return backdrop;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, BackdropParam parameter) {
        return null;
    }

    public static class BackdropParam extends AssetLoaderParameters<Backdrop> {
        int width, height;

        public BackdropParam(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}