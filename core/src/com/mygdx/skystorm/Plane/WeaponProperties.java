package com.mygdx.skystorm.Plane;

import com.badlogic.gdx.files.FileHandle;

public class WeaponProperties {

    int damage;
    float reloadTimeSeconds;
    float lifespanSeconds;
    FileHandle sprite;

    public WeaponProperties(int damage, float reloadTimeSeconds, float lifespanSeconds, FileHandle sprite) {
        this.damage = damage;
        this.reloadTimeSeconds = reloadTimeSeconds;
        this.lifespanSeconds = lifespanSeconds;
        this.sprite = sprite;
    }
}
