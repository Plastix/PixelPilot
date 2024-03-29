package com.mygdx.pixelpilot.game.plane;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponSlot;

import java.util.List;

public class OldPlaneDefinition {

    public String name;
    public int speed;
    public int armor;
    public int minTurnRadius;
    public String spritePath;
    public Color markerColor;
    public List<WeaponSlot> weaponSlots;

    @Override
    public String toString(){
        return String.format("name: %s, speed: %s, armor: %s, minTurnRadius: %s, spritePath: %s",
                name, speed, armor, minTurnRadius, spritePath);
    }

}
