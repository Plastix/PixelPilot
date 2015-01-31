package com.mygdx.skystorm.Plane;

public class PlaneFactory {

    public static Plane build(Preset planePreset){
        return new Plane(new PlaneBody(planePreset.properties, planePreset.weapon), planePreset.controller);

    }
}
