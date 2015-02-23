package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.plane.PlanePreset;

public class PlanePresetSerializer implements ScalarSerializer<PlanePreset> {

    @Override
    public String write(PlanePreset object) throws YamlException {
        return null;
    }

    @Override
    public PlanePreset read(String value) throws YamlException {
        for(PlanePreset planePreset : GameData.planePresets){
            if(value.equals(planePreset.name)){
                return planePreset;
            }
        }
        throw new YamlException("No PlanePreset with name " + value + " found!");    }
}
