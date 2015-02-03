package com.mygdx.skystorm.plane;

public class WeaponDefinition {

    public String name;
    public int damage;
    public float reloadTimeSeconds;
    public float lifespanSeconds;
    public String spritePath;

    @Override
    public String toString(){
        return String.format("name: %s, damage: %s, reload(s): %s, lifespan(s): %s",
                name, damage, reloadTimeSeconds, lifespanSeconds);
    }

}
