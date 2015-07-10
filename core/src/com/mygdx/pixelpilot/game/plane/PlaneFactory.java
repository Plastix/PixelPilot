package com.mygdx.pixelpilot.game.plane;

import com.artemis.Entity;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.game.Plane;


public class PlaneFactory {
    private Plane plane;
//    public static Plane build(PlanePreset planePreset) {
//        return build(planePreset.planeDefinition, planePreset.weaponDefinitions, planePreset.controller);
//    }
//
//    public static Plane build(PlanePreset planePreset, Class<? extends Controller> controller) {
//        return build(planePreset.planeDefinition, planePreset.weaponDefinitions, controller);
//    }


//    public Entity build(PlaneDefinition planeBody, List<InstalledWeaponDefinition> weapon, Class<? extends Controller> controller) {
////        Plane plane = plane.create()
//            Entity entity = plane.create();
//        entity.edit().add(planeBody);
//        return entity;
//    }

    public Entity blah(){
        return plane.position(100, 100).size(100,100).health(100).setPath(Assets.Images.plane).create();
    }


}
