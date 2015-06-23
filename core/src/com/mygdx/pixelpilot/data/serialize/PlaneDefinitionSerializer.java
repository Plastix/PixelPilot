package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.plane.PlaneDefinition;

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
