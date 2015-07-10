package com.mygdx.pixelpilot.game.component;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponSlot;

import java.util.List;

//TODO Move somewhere else (not component)
public class PlaneDefinition {

    public String name;
    public int speed;
    public int armor;
    public int minTurnRadius;
    public String spritePath;
    public Color markerColor;
    public List<WeaponSlot> weaponSlots;

    @Override
    public String toString() {
        return "PlaneDefinition{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", armor=" + armor +
                ", minTurnRadius=" + minTurnRadius +
                ", spritePath='" + spritePath + '\'' +
                ", markerColor=" + markerColor +
                ", weaponSlots=" + weaponSlots +
                '}';
    }
}
