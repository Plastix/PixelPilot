package com.mygdx.skystorm.data;

import com.mygdx.skystorm.plane.WeaponDefinition;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("weapons")
public class WeaponXMLMap {

    @Element(name = "weapon", targetType = WeaponDefinition.class)
    public List<WeaponDefinition> weaponDefinitions;
}
