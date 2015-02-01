package com.mygdx.skystorm.Data;

import com.mygdx.skystorm.Plane.WeaponProperty;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("weapons")
public class WeaponXMLMap {

    @Element(name = "weapon", targetType = WeaponProperty.class)
    public List<WeaponProperty> weapons;
}
