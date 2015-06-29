package com.mygdx.pixelpilot.game.plane;

import com.mygdx.pixelpilot.game.plane.controller.Controller;
import com.mygdx.pixelpilot.game.plane.controller.ControllerFactory;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.InstalledWeaponDefinition;

import java.util.List;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return build(planePreset.planeDefinition, planePreset.weaponDefinitions, planePreset.controller);
    }

    public static Plane build(PlanePreset planePreset, Class<? extends Controller> controller){
        return build(planePreset.planeDefinition, planePreset.weaponDefinitions, controller);
    }

    public static Plane build(PlaneDefinition planeBody, List<InstalledWeaponDefinition> weapon, Class<? extends Controller> controller){
        return new Plane(planeBody, weapon, ControllerFactory.build(controller));
    }
}
