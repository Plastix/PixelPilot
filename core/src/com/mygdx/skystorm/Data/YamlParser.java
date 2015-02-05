package com.mygdx.skystorm.data;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.mygdx.skystorm.data.level.Stage;
import com.mygdx.skystorm.data.level.Wave;
import com.mygdx.skystorm.data.serialize.ControllerSerializer;
import com.mygdx.skystorm.data.serialize.PlaneDefinitionSerializer;
import com.mygdx.skystorm.data.serialize.PlanePresetSerializer;
import com.mygdx.skystorm.data.serialize.WeaponDefinitionSerializer;
import com.mygdx.skystorm.plane.Plane;
import com.mygdx.skystorm.plane.PlaneDefinition;
import com.mygdx.skystorm.plane.PlanePreset;
import com.mygdx.skystorm.plane.WeaponDefinition;
import com.mygdx.skystorm.plane.controller.Controller;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class YamlParser {

    private static final String WEAPONS_YAML_PATH = "config/Weapons.yml";
    private static final String PLANE_YAML_PATH = "config/PlaneDefinitions.yml";
    private static final String PRESET_YAML_PATH = "config/PlanePresets.yml";
    private static final String LEVEL_YAML_PATH = "config/Levels.yml";

    public static void loadAllData() {
        parsePlaneWeapons();
        parsePlaneDefinitions();
        parsePlanePresets();
        parseLevels();
    }

    public static void parsePlaneWeapons() {
        GameData.weaponDefinitions = parseYamlToList(WEAPONS_YAML_PATH, WeaponDefinition.class);
    }

    public static void parsePlaneDefinitions() {
        GameData.planeDefinitions = parseYamlToList(PLANE_YAML_PATH, PlaneDefinition.class);
    }

    public static void parsePlanePresets() {
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(PlaneDefinition.class, new PlaneDefinitionSerializer());
        config.setScalarSerializer(WeaponDefinition.class, new WeaponDefinitionSerializer());
        config.setScalarSerializer(Controller.class, new ControllerSerializer());
        GameData.planePresets = parseYamlToList(PRESET_YAML_PATH, PlanePreset.class, config);
    }

    public static void parseLevels(){
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(Wave.class, new PlanePresetSerializer());
        GameData.stages = parseYamlToList(LEVEL_YAML_PATH, Stage.class, config);
    }

    private static <T> ArrayList<T> parseYamlToList(String filePath, Class<T> type) {
        return parseYamlToList(filePath, type, new YamlConfig());
    }

    private static <T> ArrayList<T> parseYamlToList(String filePath, Class<T> type, YamlConfig config) {
        try {
            File yamlFile = Gdx.files.internal(filePath).file();
            YamlReader reader = new YamlReader(new FileReader(yamlFile), config);
            ArrayList parsed = reader.read(ArrayList.class, type);
            return recastArray(parsed, type);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<T>();
    }

    private static <T> ArrayList<T> recastArray(ArrayList input, Class<T> type){
        ArrayList<T> recast = new ArrayList<T>();
        for(Object object : input){
            if(object.getClass().equals(type)){
                recast.add(type.cast(object));
            }
        }
        return recast;
    }

}
