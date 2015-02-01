package com.mygdx.skystorm.Plane;

import com.mygdx.skystorm.Data.converter.PlanePropertyConverter;
import com.mygdx.skystorm.Data.converter.WeaponPropertyConverter;
import com.mygdx.skystorm.Plane.Controller.Controller;
import org.xmappr.Attribute;
import org.xmappr.Element;

public class PlanePreset {

    @Attribute
    public String name;

    @Element(name="plane-property", converter = PlanePropertyConverter.class)
    public PlaneProperty planeProperty;

    @Element(name="weapon", converter = WeaponPropertyConverter.class)
    public WeaponProperty weaponProperty;

    public Controller controller;

}
