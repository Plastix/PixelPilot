package com.mygdx.skystorm.data.converter;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.plane.PlaneDefinition;

public class PlaneDefinitionSerializer implements ScalarSerializer<PlaneDefinition> {

    @Override
    public String write(PlaneDefinition object) throws YamlException {
        return null;
    }

    @Override
    public PlaneDefinition read(String value) throws YamlException {
        for(PlaneDefinition planeDefinition : GameData.planeDefinitions){
            if(value.equals(planeDefinition.name)){
                return planeDefinition;
            }
        }
        throw new YamlException("No PlaneDefinition with name " + value + " found!");
    }
}
