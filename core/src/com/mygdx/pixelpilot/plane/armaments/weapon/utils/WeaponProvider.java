package com.mygdx.pixelpilot.plane.armaments.weapon.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.armaments.weapon.weapons.Weapon;

import java.util.HashMap;
import java.util.Map;

public abstract class WeaponProvider<T extends Weapon> {
    Map<String, Pool<Sprite>> spritePools = new HashMap<String, Pool<Sprite>>();
    public T obtain(Plane owner, WeaponDefinition def) {
        return create(owner, def);
    }
    public abstract T create(Plane owner, WeaponDefinition def);
}
