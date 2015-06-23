package com.mygdx.pixelpilot.data.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;

// todo: allow creation of backdrops from any theme. Should be part of the parameter.
public class BackdropLoader extends AsynchronousAssetLoader<Backdrop, BackdropLoader.BackdropParam> {
    private BackdropTheme theme;

    public BackdropLoader(FileHandleResolver resolver) {
        super(resolver);
    }


    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, BackdropParam parameter) {
        theme = BackdropFactory.buildTheme(parameter.theme);
    }

    @Override
    public Backdrop loadSync(AssetManager manager, String fileName, FileHandle file, BackdropParam parameter) {
        return BackdropFactory.buildBackdrop(parameter.width, parameter.height, 4, theme);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, BackdropParam parameter) {
        return null;
    }

    public static class BackdropParam extends AssetLoaderParameters<Backdrop> {
        int width;
        int height;
        private BackdropFactory.ThemePreset theme;

        public BackdropParam(int width, int height, BackdropFactory.ThemePreset theme) {
            this.width = width;
            this.height = height;
            this.theme = theme;
        }
    }
}