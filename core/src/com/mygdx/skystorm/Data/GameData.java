package com.mygdx.skystorm.data;

import com.mygdx.skystorm.data.level.Stage;
import com.mygdx.skystorm.plane.PlanePreset;
import com.mygdx.skystorm.plane.PlaneDefinition;
import com.mygdx.skystorm.plane.WeaponDefinition;

import java.util.List;

public abstract class GameData {

    public static List<WeaponDefinition> weaponDefinitions;
    public static List<PlaneDefinition> planeDefinitions;
    public static List<PlanePreset> planePresets;
    public static List<Stage> stages;
}