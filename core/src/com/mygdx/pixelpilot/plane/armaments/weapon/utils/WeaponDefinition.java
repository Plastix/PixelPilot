package com.mygdx.pixelpilot.plane.armaments.weapon.utils;

import com.mygdx.pixelpilot.plane.armaments.projectile.projectiles.Projectile;

import java.util.Map;

public class WeaponDefinition {

    public String name;
    public int damage;
    public float velocity;
    public WeaponTypeContainer weaponType;
    public Class<? extends Projectile> projectileType;
    public Map weaponProperties;
    public float reloadTime;
    public float lifespanSeconds;
    public float weight;
    public String spritePath;

    @Override
    public String toString() {
        return String.format("name: %s, damage: %s, reload(s): %s, lifespan(s): %s, weight: %s",
                name, damage, reloadTime, lifespanSeconds, weight);
    }
}
