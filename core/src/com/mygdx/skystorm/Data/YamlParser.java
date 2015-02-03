package com.mygdx.skystorm.data;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.mygdx.skystorm.data.serialize.ControllerSerializer;
import com.mygdx.skystorm.data.serialize.PlaneDefinitionSerializer;
import com.mygdx.skystorm.data.serialize.WeaponDefinitionSerializer;
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
    private static final String PLANE_YAML_PATH = "config/PlaneProperties.yml";
    private static final String PRESET_YAML_PATH = "config/PlanePresets.yml";

    public static void parsePlaneWeapons() {
        ArrayList parsed = parseYamlToList(WEAPONS_YAML_PATH, WeaponDefinition.class);
        List<WeaponDefinition> weapons = new ArrayList<WeaponDefinition>();
        for (Object object : parsed) {
            if (object instanceof WeaponDefinition) {
                WeaponDefinition cast = (WeaponDefinition) object;
                weapons.add(cast);
            }
        }
        GameData.weaponDefinitions = weapons;
    }

    public static void loadAllData() {
        parsePlaneWeapons();
        parsePlaneProperties();
        parsePlanePresets();
    }

    public static void parsePlaneProperties() {
        ArrayList parsed = parseYamlToList(PLANE_YAML_PATH, PlaneDefinition.class);
        List<PlaneDefinition> planeProperties = new ArrayList<PlaneDefinition>();
        for (Object object : parsed) {
            if (object instanceof PlaneDefinition) {
                PlaneDefinition cast = (PlaneDefinition) object;
                planeProperties.add(cast);
            }
        }
        GameData.planeDefinitions = planeProperties;
    }

    public static void parsePlanePresets() {
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(PlaneDefinition.class, new PlaneDefinitionSerializer());
        config.setScalarSerializer(WeaponDefinition.class, new WeaponDefinitionSerializer());
        config.setScalarSerializer(Controller.class, new ControllerSerializer());

        ArrayList parsed = parseYamlToList(PRESET_YAML_PATH, PlanePreset.class, config);
        List<PlanePreset> planePresets = new ArrayList<PlanePreset>();
        for (Object object : parsed) {
            if (object instanceof PlanePreset) {
                PlanePreset cast = (PlanePreset) object;
                planePresets.add(cast);
            }
        }
        GameData.planePresets = planePresets;
    }

    private static ArrayList parseYamlToList(String filePath, Class map) {
        return parseYamlToList(filePath, map, new YamlConfig());
    }

    private static ArrayList parseYamlToList(String filePath, Class map, YamlConfig config) {
        try {
            File yamlFile = Gdx.files.internal(filePath).file();
            YamlReader reader = new YamlReader(new FileReader(yamlFile), config);
            return reader.read(ArrayList.class, map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList();
    }

}
