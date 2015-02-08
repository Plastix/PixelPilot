package com.mygdx.skystorm.plane;

import com.mygdx.skystorm.plane.controller.PlayerController;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return new Plane(new PlaneActor(planePreset.planeDefinition, planePreset.weaponDefinition), planePreset.controller);

    }

    public static Plane build(PlaneDefinition planeBody, WeaponDefinition weapon, PlayerController controller){
        return new Plane(new PlaneActor(planeBody, weapon), controller);
    }
}
