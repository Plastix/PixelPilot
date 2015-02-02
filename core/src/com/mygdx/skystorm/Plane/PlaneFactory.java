package com.mygdx.skystorm.plane;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return new Plane(new PlaneBody(planePreset.planeProperty, planePreset.weaponProperty), planePreset.controller);

    }
}
