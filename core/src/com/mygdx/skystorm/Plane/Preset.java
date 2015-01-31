package com.mygdx.skystorm.Plane;

import com.mygdx.skystorm.Plane.Controller.Controller;

public class Preset {

    Properties properties;
    Weapon weapon;
    Controller controller;

    public Preset(Properties properties, Weapon weapon, Controller controller) {
        this.properties = properties;
        this.weapon = weapon;
        this.controller = controller;
    }
}
