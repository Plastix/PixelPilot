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
import com.mygdx.pixelpilot.data.serialize.*;
import com.mygdx.pixelpilot.plane.PlaneDefinition;
import com.mygdx.pixelpilot.plane.PlanePreset;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.InstalledWeaponDefinition;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponDefinition;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponSlot;

import java.util.ArrayList;

public class PlanePresetLoader extends AsynchronousAssetLoader<PlanePresetLoader.PlanePresetContainer, PlanePresetLoader.PlanePresetParam> {
    private PlanePresetContainer weaponDefinitions;

    public PlanePresetLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, PlanePresetParam parameter) {
        return null;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, PlanePresetParam parameter) {
        weaponDefinitions = load(fileName);
    }

    @Override
    public PlanePresetContainer loadSync(AssetManager manager, String fileName, FileHandle file, PlanePresetParam parameter) {
        return weaponDefinitions;
    }

    private PlanePresetContainer load(String fileName){
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(PlaneDefinition.class, new PlaneDefinitionSerializer());
        config.setPropertyElementType(PlanePreset.class, "weaponDefinitions", InstalledWeaponDefinition.class);
        config.setScalarSerializer(WeaponDefinition.class, new WeaponDefinitionSerializer());
        config.setScalarSerializer(WeaponSlot.class, new WeaponSlotNameSerializer());
        config.setScalarSerializer(Class.class, new ControllerSerializer());
        PlanePresetContainer c = new PlanePresetContainer();
        c.planePresets = YamlLoaderUtils.parseYamlToList(fileName, PlanePreset.class, config);
        GameData.planePresets = c.planePresets;
        return c;
    }

    public static class PlanePresetParam extends AssetLoaderParameters<PlanePresetContainer> {

    }

    public class PlanePresetContainer {
        ArrayList<PlanePreset> planePresets;
    }
}
