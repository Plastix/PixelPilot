package com.mygdx.skystorm.plane.controller;

import java.util.HashMap;


public class ControllerFactory {
//    static {
//        ControllerFactory.register(PlayerController.class);
//    }

    private static HashMap<String, Class> controllers = new HashMap<String, Class>();;

    protected static void register(Class c){
        System.out.println("registering "+c.getName());
        controllers.put("BasicAIController", c);
    }

    public static Controller build(String name){
        // TODO: Find a better way to do this

        if(name.equals("BasicAIController")){
            return new BasicAIController();
        }else if(name.equals("PlayerController")){
            return new PlayerController();
        }

        throw new RuntimeException("Controller "+name+" not found. Make sure the name in the XML matches the class name.");
    }

}
