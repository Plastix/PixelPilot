package com.mygdx.skystorm.data.converter;

import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.plane.WeaponDefinition;
import org.xmappr.converters.ValueConverter;

public class WeaponDefinitionConverter extends ValueConverter {

    @Override
    public Object fromValue(String value, String format, Class targetType, Object targetObject) {
        for(WeaponDefinition weaponDefinition : GameData.weaponDefinitions){
            if(value.equals(weaponDefinition.name)){
                return weaponDefinition;
            }
        }
        return null;
    }

    @Override
    public String toValue(Object object, String format) {
        return null;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(WeaponDefinition.class);
    }
}
