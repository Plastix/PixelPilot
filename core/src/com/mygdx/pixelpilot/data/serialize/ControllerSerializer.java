package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.plane.controller.Controller;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;

public class ControllerSerializer implements ScalarSerializer<Controller> {

    @Override
    public String write(Controller object) throws YamlException {
        return null;
    }

    @Override
    public Controller read(String value) throws YamlException {
        if(ControllerFactory.isValidAIController(value)){
            return ControllerFactory.build(value);
        }else {
            throw new YamlException("No Controller with name " + value + " found!");
        }
    }
}
