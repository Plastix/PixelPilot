package com.mygdx.pixelpilot.data.loader;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.Reader;
import java.util.ArrayList;

public class YamlLoaderUtils {
    public static <T> ArrayList<T> parseYamlToList(String filePath, Class<T> type, YamlConfig config) {
        try {
            Reader yamlFile = Gdx.files.internal(filePath).reader();
            YamlReader reader = new YamlReader(yamlFile, config);
            ArrayList parsed = reader.read(ArrayList.class, type);
            return recastArray(parsed, type);
        } catch (YamlException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    public static <T> ArrayList<T> recastArray(ArrayList input, Class<T> type) {
        ArrayList<T> recast = new ArrayList<T>();
        for (Object object : input) {
            if (object.getClass().equals(type)) {
                recast.add(type.cast(object));
            }
        }
        return recast;
    }
}
