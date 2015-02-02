package com.mygdx.skystorm.plane;

import org.xmappr.Attribute;
import org.xmappr.Element;

public class WeaponProperty {

    @Attribute
    public String name;

    @Element
    public int damage;

    @Element("reload-time")
    public float reloadTimeSeconds;

    @Element("lifespan")
    public float lifespanSeconds;

    @Element
    public String sprite;
}
