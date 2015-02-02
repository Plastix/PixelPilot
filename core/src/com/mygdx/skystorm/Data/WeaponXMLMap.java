package com.mygdx.skystorm.data;

import com.mygdx.skystorm.plane.WeaponProperty;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("weapons")
public class WeaponXMLMap {

    @Element(name = "weapon", targetType = WeaponProperty.class)
    public List<WeaponProperty> weapons;
}
