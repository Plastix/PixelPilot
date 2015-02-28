package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;

public class ControllerSerializer implements ScalarSerializer<Class> {

    @Override
    public String write(Class object) throws YamlException {
        return null;
    }

    @Override
    public Class read(String value) throws YamlException {

        for(Class controller : ControllerFactory.getControllers()) {
            if(controller.getSimpleName().equals(value)) {
                return controller;
            }
        }
        throw new YamlException("No Controller with name " + value + " found!");
    }
}
