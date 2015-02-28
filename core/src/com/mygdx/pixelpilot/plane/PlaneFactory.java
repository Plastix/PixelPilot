package com.mygdx.pixelpilot.plane;

import com.mygdx.pixelpilot.plane.controller.ControllerFactory;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return build(planePreset.planeDefinition, planePreset.weaponDefinition, planePreset.controller);
    }

    public static Plane build(PlanePreset planePreset, Class controller){
        return build(planePreset.planeDefinition, planePreset.weaponDefinition, controller);
    }

    public static Plane build(PlaneDefinition planeBody, WeaponDefinition weapon, Class controller){
        return new Plane(planeBody, weapon, ControllerFactory.build(controller));
    }
}
