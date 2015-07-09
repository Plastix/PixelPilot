package com.mygdx.pixelpilot.game.plane.armaments.weapon.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.pixelpilot.game.plane.OldPlane;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.weapons.Weapon;

import java.util.HashMap;
import java.util.Map;

public abstract class WeaponProvider<T extends Weapon> {
    Map<String, Pool<Sprite>> spritePools = new HashMap<String, Pool<Sprite>>();
    public T obtain(OldPlane owner, WeaponDefinition def) {
        return create(owner, def);
    }
    public abstract T create(OldPlane owner, WeaponDefinition def);
}
