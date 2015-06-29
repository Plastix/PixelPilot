package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.game.plane.controller.Controller;
import com.mygdx.pixelpilot.game.plane.controller.ControllerFactory;

public class ControllerSerializer implements ScalarSerializer<Class<? extends Controller>> {

    @Override
    public String write(Class<? extends Controller> object) throws YamlException {
        return null;
    }

    @Override
    public Class<? extends Controller> read(String value) throws YamlException {

        for(Class<? extends Controller> controller : ControllerFactory.getControllers()) {
            if(controller.getSimpleName().equals(value)) {
                return controller;
            }
        }
        throw new YamlException("No Controller with name " + value + " found!");
    }
}
