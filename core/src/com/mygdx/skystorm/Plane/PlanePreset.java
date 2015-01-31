package com.mygdx.skystorm.Plane;

import com.mygdx.skystorm.Plane.Controller.Controller;

public class PlanePreset {

    PlaneProperties planeProperties;
    WeaponProperties weaponProperties;
    Controller controller;

    public PlanePreset(PlaneProperties planeProperties, WeaponProperties weaponProperties, Controller controller) {
        this.planeProperties = planeProperties;
        this.weaponProperties = weaponProperties;
        this.controller = controller;
    }
}
