package com.mygdx.skystorm.plane;

import org.xmappr.Attribute;
import org.xmappr.Element;

public class PlaneDefinition {

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

    @Override
    public String toString(){
        return String.format("name: %s, speed: %s, armor: %s, turnSpeed: %s, spritePath: %s",
                name, speed, armor, turnSpeed, sprite);
    }

}
