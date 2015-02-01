package com.mygdx.skystorm.Plane;

public class PlaneFactory {

    public static Plane build(PlanePreset planePreset){
        return new Plane(new PlaneBody(planePreset.planeProperty, planePreset.weaponProperty), planePreset.controller);

    }
}
