package com.mygdx.pixelpilot.plane.shooty.weapon.utils;

import com.badlogic.gdx.math.Vector2;

public class WeaponSlot {
    public String name;
    public Vector2 position;

    @Override
    public String toString() {
        return "(" + name + ", " + position + ")";
    }
}
