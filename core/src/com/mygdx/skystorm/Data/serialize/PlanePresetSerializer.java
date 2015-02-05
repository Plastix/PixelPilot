package com.mygdx.skystorm.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.plane.PlanePreset;

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
