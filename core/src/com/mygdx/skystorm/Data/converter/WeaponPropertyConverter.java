package com.mygdx.skystorm.data.converter;

import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.plane.WeaponProperty;
import org.xmappr.converters.ValueConverter;

public class WeaponPropertyConverter extends ValueConverter {

    @Override
    public Object fromValue(String value, String format, Class targetType, Object targetObject) {
        for(WeaponProperty weaponProperty : GameData.weapons){
            if(value.equals(weaponProperty.name)){
                return weaponProperty;
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
        return type.equals(WeaponProperty.class);
    }
}
