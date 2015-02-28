package com.mygdx.pixelpilot.plane;

import com.mygdx.pixelpilot.plane.controller.Controller;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return build(planePreset.planeDefinition, planePreset.weaponDefinition, planePreset.controller);
    }

    public static Plane build(PlanePreset planePreset, Controller controller){
        return build(planePreset.planeDefinition, planePreset.weaponDefinition, controller);
    }

    public static Plane build(PlaneDefinition planeBody, WeaponDefinition weapon, Controller controller){
        return new Plane(planeBody, weapon, controller);
    }
}
