package com.mygdx.pixelpilot.data.serialize;

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;

public class ColorSerializer implements ScalarSerializer<Color> {

    @Override
    public String write(Color object) throws YamlException {
        return null;
    }

    @Override
    public Color read(String value) throws YamlException {
        Color color;
        try {
             color = Color.valueOf(value);
        }catch (NumberFormatException e){
            throw new YamlException("Error parsing color of string " + value + "!");
        }
        return color;
    }
}
