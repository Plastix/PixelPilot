package com.mygdx.pixelpilot.plane;

public class WeaponDefinition {

    public String name;
    public int damage;
    public float reloadTimeSeconds;
    public float lifespanSeconds;
    public float weight;
    public String spritePath;

    @Override
    public String toString(){
        return String.format("name: %s, damage: %s, reload(s): %s, lifespan(s): %s, weight: %s",
                name, damage, reloadTimeSeconds, lifespanSeconds, weight);
    }

}
