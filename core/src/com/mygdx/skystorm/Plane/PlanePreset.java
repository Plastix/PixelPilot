package com.mygdx.skystorm.plane;

import com.mygdx.skystorm.data.converter.PlanePropertyConverter;
import com.mygdx.skystorm.data.converter.WeaponPropertyConverter;
import com.mygdx.skystorm.plane.controller.Controller;
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
