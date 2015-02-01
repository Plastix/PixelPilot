package com.mygdx.skystorm.Plane;

import org.xmappr.Attribute;
import org.xmappr.Element;

public class PlaneProperty {

    @Attribute
    public String name;

    @Element
    public int speed;

    @Element
    public int armor;

    @Element("turn-speed")
    public int turnSpeed;

    @Element
    public String sprite;

}
