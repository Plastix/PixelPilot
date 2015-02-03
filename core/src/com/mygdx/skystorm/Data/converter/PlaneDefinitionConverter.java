package com.mygdx.skystorm.data.converter;

import com.mygdx.skystorm.data.GameData;
import com.mygdx.skystorm.plane.PlaneDefinition;
import org.xmappr.converters.ValueConverter;

public class PlaneDefinitionConverter extends ValueConverter {

    @Override
    public Object fromValue(String value, String format, Class targetType, Object targetObject) {
        for(PlaneDefinition planeDefinition : GameData.planeDefinitions){
            if(value.equals(planeDefinition.name)){
                return planeDefinition;
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
        return type.equals(PlaneDefinition.class);
    }
}
