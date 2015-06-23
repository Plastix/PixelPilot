
package com.mygdx.pixelpilot.data.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.data.serialize.ColorSerializer;
import com.mygdx.pixelpilot.data.serialize.Vector2Serializer;
import com.mygdx.pixelpilot.plane.PlaneDefinition;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponSlot;

import java.util.ArrayList;

public class PlaneDefinitionLoader extends AsynchronousAssetLoader<PlaneDefinitionLoader.PlaneDefinitionContainer, PlaneDefinitionLoader.PlaneDefParam> {
    PlaneDefinitionContainer planeDefinitions;

    public PlaneDefinitionLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, PlaneDefParam parameter) {
        return null;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, PlaneDefParam parameter) {
        planeDefinitions = load(fileName);
    }

    @Override
    public PlaneDefinitionContainer loadSync(AssetManager manager, String fileName, FileHandle file, PlaneDefParam parameter) {
        return load(fileName);
    }

    private PlaneDefinitionContainer load(String fileName) {
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(Color.class, new ColorSerializer());
        config.setPropertyElementType(PlaneDefinition.class, "weaponSlots", WeaponSlot.class);
        config.setScalarSerializer(Vector2.class, new Vector2Serializer());
        PlaneDefinitionContainer c = new PlaneDefinitionContainer();
        c.planeDefs = YamlLoaderUtils.parseYamlToList(fileName, PlaneDefinition.class, config);
        GameData.planeDefinitions = c.planeDefs;
        return c;
    }

    public static class PlaneDefParam extends AssetLoaderParameters<PlaneDefinitionContainer> {

    }

    public class PlaneDefinitionContainer {
        public ArrayList<PlaneDefinition> planeDefs;
    }
}
