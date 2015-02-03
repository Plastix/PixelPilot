package com.mygdx.skystorm.plane;

import com.mygdx.skystorm.plane.controller.Controller;

public class PlanePreset {

    public String name;
    public PlaneDefinition planeDefinition;
    public WeaponDefinition weaponDefinition;
    public Controller controller;

    @Override
    public String toString(){
        return String.format("PlanePreset (%s), using plane %s and weapon %s with controller %s",
                name, planeDefinition, weaponDefinition, controller);
    }

}
