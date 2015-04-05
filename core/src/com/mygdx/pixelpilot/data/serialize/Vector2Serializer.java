package com.mygdx.pixelpilot.data.serialize;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vector2Serializer implements ScalarSerializer<Vector2> {
    @Override
    public String write(Vector2 object) throws YamlException {
        return null;
    }

    @Override
    public Vector2 read(String value) throws YamlException {
        Vector2 vec;
        String[] split = value.split(",");
        try {
            vec = new Vector2(Float.valueOf(split[0]), Float.valueOf(split[1]));
        }catch (NumberFormatException e) {
            throw new YamlException("Error parsing vector from string " + value + "!");
        }
        return vec;
    }

}
