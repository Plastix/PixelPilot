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
import com.mygdx.pixelpilot.data.serialize.ProjectileSerializer;
import com.mygdx.pixelpilot.data.serialize.WeaponSerializer;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponDefinition;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponTypeContainer;

import java.util.ArrayList;

// todo: make this whole loader thing nicer
public class WeaponDefinitionLoader extends AsynchronousAssetLoader<WeaponDefinitionLoader.WeaponDefinitionContainer, WeaponDefinitionLoader.WepDefParam> {
    private WeaponDefinitionContainer weaponDefinitions;

    public WeaponDefinitionLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, WepDefParam parameter) {
        return null;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, WepDefParam parameter) {
        weaponDefinitions = load(fileName);
    }

    @Override
    public WeaponDefinitionContainer loadSync(AssetManager manager, String fileName, FileHandle file, WepDefParam parameter) {
        return weaponDefinitions;
    }

    private WeaponDefinitionContainer load(String fileName) {
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(Class.class, new ProjectileSerializer());
        config.setScalarSerializer(WeaponTypeContainer.class, new WeaponSerializer());
        WeaponDefinitionContainer c = new WeaponDefinitionContainer();
        c.weaponDefs = YamlLoaderUtils.parseYamlToList(fileName, WeaponDefinition.class, config);
        // todo: fix - don't like having to set GameData values manually
        GameData.weaponDefinitions = c.weaponDefs;
        return c;
    }

    public static class WeaponDefinitionContainer {
        ArrayList<WeaponDefinition> weaponDefs;
    }

    public static class WepDefParam extends AssetLoaderParameters<WeaponDefinitionContainer> {

    }
}
