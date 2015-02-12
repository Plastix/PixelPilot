package com.mygdx.skystorm.plane;

import com.mygdx.skystorm.plane.controller.Controller;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return build(planePreset.planeDefinition, planePreset.weaponDefinition, planePreset.controller);
    }

    public static Plane build(PlanePreset planePreset, Controller controller){
        return build(planePreset.planeDefinition, planePreset.weaponDefinition, controller);
    }

    public static Plane build(PlaneDefinition planeBody, WeaponDefinition weapon, Controller controller){
        return new Plane(new PlaneActor(planeBody, weapon), controller);
    }
}
