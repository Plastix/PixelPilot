package com.mygdx.skystorm.data.converter;

import com.mygdx.skystorm.plane.WeaponDefinition;
import com.mygdx.skystorm.plane.controller.Controller;
import com.mygdx.skystorm.plane.controller.ControllerFactory;
import com.mygdx.skystorm.plane.controller.PlayerController;
import org.xmappr.converters.ValueConverter;

public class ControllerConverter extends ValueConverter {

    @Override
    public Object fromValue(String value, String format, Class targetType, Object targetObject) {
        Controller c = ControllerFactory.build(value);
        return c;
    }

    @Override
    public String toValue(Object object, String format) {
        return null;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Controller.class);
    }
}
