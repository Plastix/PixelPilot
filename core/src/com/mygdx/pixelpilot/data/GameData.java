package com.mygdx.pixelpilot.data;

import com.mygdx.pixelpilot.data.level.Level;
import com.mygdx.pixelpilot.plane.PlanePreset;
import com.mygdx.pixelpilot.plane.PlaneDefinition;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponDefinition;

import java.util.List;

public abstract class GameData {

    public static List<WeaponDefinition> weaponDefinitions;
    public static List<PlaneDefinition> planeDefinitions;
    public static List<PlanePreset> planePresets;
    public static List<Level> levels;
}