package com.mygdx.skystorm.Plane;

public class PlaneFactory {

    public static Plane build(PlanePreset planePlanePreset){
        return new Plane(new PlaneBody(planePlanePreset.planeProperties, planePlanePreset.weaponProperties), planePlanePreset.controller);

    }
}
