package com.mygdx.skystorm.plane;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return new Plane(new PlaneActor(planePreset.planeDefinition, planePreset.weaponDefinition), planePreset.controller);

    }
}
