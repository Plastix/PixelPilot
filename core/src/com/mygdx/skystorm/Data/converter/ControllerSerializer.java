package com.mygdx.skystorm.data.converter;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.skystorm.plane.controller.Controller;
import com.mygdx.skystorm.plane.controller.ControllerFactory;

public class ControllerSerializer implements ScalarSerializer<Controller> {

    @Override
    public String write(Controller object) throws YamlException {
        return null;
    }

    @Override
    public Controller read(String value) throws YamlException {
        //TODO Move logic out of controller factor into here
        return ControllerFactory.build(value);
    }
}
