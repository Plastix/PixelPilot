package com.mygdx.skystorm.plane;

import org.xmappr.Attribute;
import org.xmappr.Element;

public class WeaponDefinition {

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

    @Override
    public String toString(){
        return String.format("name: %s, damage: %s, reload(s): %s, lifespan(s): %s",
                name, damage, reloadTimeSeconds, lifespanSeconds);
    }
}
