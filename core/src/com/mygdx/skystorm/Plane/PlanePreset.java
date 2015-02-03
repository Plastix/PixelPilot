package com.mygdx.skystorm.plane;

import com.mygdx.skystorm.data.converter.ControllerConverter;
import com.mygdx.skystorm.data.converter.PlaneDefinitionConverter;
import com.mygdx.skystorm.data.converter.WeaponDefinitionConverter;
import com.mygdx.skystorm.plane.controller.Controller;
import org.xmappr.Attribute;
import org.xmappr.Element;

public class PlanePreset {

    @Attribute
    public String name;

    @Element(name="plane-definition", converter = PlaneDefinitionConverter.class)
    public PlaneDefinition planeDefinition;

    @Element(name="weapon", converter = WeaponDefinitionConverter.class)
    public WeaponDefinition weaponDefinition;

    @Element(name="controller", converter = ControllerConverter.class)
    public Controller controller;

    @Override
    public String toString(){
        return String.format("PlanePreset (%s), using plane %s and weapon %s with controller %s",
                name, planeDefinition, weaponDefinition, controller);
    }

}
