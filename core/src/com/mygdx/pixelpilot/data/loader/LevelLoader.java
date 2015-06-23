package com.mygdx.pixelpilot.data.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.level.Level;
import com.mygdx.pixelpilot.data.level.Wave;
import com.mygdx.pixelpilot.data.serialize.BackdropThemeSerializer;
import com.mygdx.pixelpilot.data.serialize.PlanePresetSerializer;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;
import com.mygdx.pixelpilot.plane.PlanePreset;

import java.util.ArrayList;

public class LevelLoader extends AsynchronousAssetLoader<LevelLoader.LevelContainer, LevelLoader.LevelLoaderParam> {
    private LevelContainer levels;

    public LevelLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, LevelLoaderParam parameter) {
        return null;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, LevelLoaderParam parameter) {
        levels = load(fileName);
    }

    @Override
    public LevelContainer loadSync(AssetManager manager, String fileName, FileHandle file, LevelLoaderParam parameter) {
        return levels;
    }

    private LevelContainer load(String fileName) {
        YamlConfig config = new YamlConfig();
        config.setPropertyElementType(Level.class, "waves", Wave.class);
        config.setPropertyElementType(Wave.class, "enemies", PlanePreset.class);
        config.setScalarSerializer(PlanePreset.class, new PlanePresetSerializer());
        config.setScalarSerializer(BackdropTheme.class, new BackdropThemeSerializer());
        LevelContainer c = new LevelContainer();
        c.levels = YamlLoaderUtils.parseYamlToList(fileName, Level.class, config);
        GameData.levels = c.levels;
        return c;
    }

    public static class LevelLoaderParam extends AssetLoaderParameters<LevelContainer> {

    }

    public class LevelContainer {
        ArrayList<Level> levels;
    }
}
