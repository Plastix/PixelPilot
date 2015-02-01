package com.mygdx.skystorm.Data.converter;

import com.mygdx.skystorm.Data.GameData;
import com.mygdx.skystorm.Plane.PlaneProperty;
import org.xmappr.converters.ValueConverter;

public class PlanePropertyConverter extends ValueConverter {

    @Override
    public Object fromValue(String value, String format, Class targetType, Object targetObject) {
        for(PlaneProperty planeProperty : GameData.planeProperties){
            if(value.equals(planeProperty.name)){
                return planeProperty;
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
        return type.equals(PlaneProperty.class);
    }
}
