package com.mygdx.pixelpilot.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.mygdx.pixelpilot.data.level.Level;
import com.mygdx.pixelpilot.data.level.Wave;
import com.mygdx.pixelpilot.data.serialize.*;
import com.mygdx.pixelpilot.plane.PlaneDefinition;
import com.mygdx.pixelpilot.plane.PlanePreset;
import com.mygdx.pixelpilot.plane.WeaponDefinition;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;

import java.io.Reader;
import java.util.ArrayList;

public class YamlParser {

    public static void loadAllData() {
        parsePlaneWeapons();
        parsePlaneDefinitions();
        parsePlanePresets();
        parseLevels();
    }

    public static void parsePlaneWeapons() {
        GameData.weaponDefinitions = parseYamlToList(Assets.data.weapons, WeaponDefinition.class);
    }

    public static void parsePlaneDefinitions() {
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(Color.class, new ColorSerializer());
        GameData.planeDefinitions = parseYamlToList(Assets.data.planes, PlaneDefinition.class, config);
    }

    public static void parsePlanePresets() {
        YamlConfig config = new YamlConfig();
        config.setScalarSerializer(PlaneDefinition.class, new PlaneDefinitionSerializer());
        config.setScalarSerializer(WeaponDefinition.class, new WeaponDefinitionSerializer());
        config.setScalarSerializer(Class.class, new ControllerSerializer());
        GameData.planePresets = parseYamlToList(Assets.data.presets, PlanePreset.class, config);
    }
    
    public static void parseLevels(){
        YamlConfig config = new YamlConfig();
        config.setPropertyElementType(Level.class, "waves", Wave.class);
        config.setPropertyElementType(Wave.class, "enemies", PlanePreset.class);
        config.setScalarSerializer(PlanePreset.class, new PlanePresetSerializer());
        config.setScalarSerializer(BackdropTheme.class, new BackdropThemeSerializer());
        GameData.levels = parseYamlToList(Assets.data.levels, Level.class, config);
    }

    private static <T> ArrayList<T> parseYamlToList(String filePath, Class<T> type) {
        return parseYamlToList(filePath, type, new YamlConfig());
    }

    private static <T> ArrayList<T> parseYamlToList(String filePath, Class<T> type, YamlConfig config) {
        try {
            Reader yamlFile = Gdx.files.internal(filePath).reader();
            YamlReader reader = new YamlReader(yamlFile, config);
            ArrayList parsed = reader.read(ArrayList.class, type);
            return recastArray(parsed, type);
        } catch (YamlException e) {
            System.out.println(e.getCause().getMessage());
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
