package com.mygdx.pixelpilot.game.plane.controller;

import java.util.ArrayList;
import java.util.List;


public class ControllerFactory {

    private static List<Class<? extends Controller>> controllers = new ArrayList<Class<? extends Controller>>();

    public static void registerControllers(){
        ControllerFactory.register(PlayerController.class);
        ControllerFactory.register(BasicAIController.class);
    }

    private static void register(Class<? extends Controller> c){
        System.out.println("Registering Controller " + c.getSimpleName() + "...");
        controllers.add(c);
    }

    public static Controller build(Class<? extends Controller> controller) {
        if(isValidController(controller)){
            try {
                return controller.newInstance();
            }catch(Exception e){
                System.out.println(e.getMessage());
                throw new RuntimeException("Exception building controller " + controller);
            }
        }else{
            throw new RuntimeException("Controller of name " + controller + " not found! Make sure the controller is registered and you are spelling it correctly!");
        }
    }

    public static boolean isValidController(Class controller){
        return controllers.contains(controller);
    }

    public static boolean isValidAIController(Class controller){
        return isValidAIController(controller) && controller.getSuperclass().equals(AIController.class);
    }

    public static List<Class<? extends Controller>> getControllers() {
        return controllers;
    }
}
