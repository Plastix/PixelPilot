package com.mygdx.pixelpilot.plane;

import com.mygdx.pixelpilot.plane.controller.Controller;
import com.mygdx.pixelpilot.plane.controller.ControllerFactory;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.InstalledWeaponDefinition;

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
