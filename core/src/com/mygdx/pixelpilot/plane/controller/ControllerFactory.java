package com.mygdx.pixelpilot.plane.controller;

import java.util.HashMap;


public class ControllerFactory {

    private static HashMap<String, Class<? extends Controller>> controllers = new HashMap<String, Class<? extends Controller>>();

    public static void registerControllers(){
        ControllerFactory.register(PlayerController.class);
        ControllerFactory.register(BasicAIController.class);
    }

    private static void register(Class<? extends Controller> c){
        String name = c.getSimpleName();
        System.out.println("Registering Controller " + name + "...");
        controllers.put(name, c);
    }

    public static Controller build(String controllerName) {
        if(isValidController(controllerName)){
            try {
                return controllers.get(controllerName).newInstance();
            }catch(Exception e){
                System.out.println(e.getMessage());
                throw new RuntimeException("Exception building controller " + controllerName);
            }
        }else{
            throw new RuntimeException("Controller of name " + controllerName + " not found! Make sure the controller is registered and you are spelling it correctly!");
        }
    }

    public static boolean isValidController(String name){
        return controllers.get(name) != null;
    }

    public static boolean isValidAIController(String name){
        Class controller = controllers.get(name);
        return controller != null && controller.getSuperclass().equals(AIController.class);
    }

}
