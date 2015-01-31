package com.mygdx.skystorm.Plane;

import com.badlogic.gdx.files.FileHandle;

public class Weapon {

    int damage;
    float reloadTimeSeconds;
    float lifespanSeconds;
    FileHandle sprite;

    public Weapon(int damage, float reloadTimeSeconds, float lifespanSeconds, FileHandle sprite) {
        this.damage = damage;
        this.reloadTimeSeconds = reloadTimeSeconds;
        this.lifespanSeconds = lifespanSeconds;
        this.sprite = sprite;
    }
}
