package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponDefinition;

public class WeaponDefinitionSerializer implements ScalarSerializer<WeaponDefinition> {

    @Override
    public String write(WeaponDefinition object) throws YamlException {
        return null;
    }

    @Override
    public WeaponDefinition read(String value) throws YamlException {
        for(WeaponDefinition weaponDefinition : GameData.weaponDefinitions){
            if(value.equals(weaponDefinition.name)){
                return weaponDefinition;
            }
        }
        throw new YamlException("No WeaponDefinition with name " + value + " found!");
    }
}
