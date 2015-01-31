package com.mygdx.skystorm.Plane;

import com.badlogic.gdx.files.FileHandle;

public class Properties {

    int speed;
    int armor;
    int turnSpeed;
    FileHandle sprite;

    public Properties(int speed, int armor, int turnSpeed, FileHandle sprite) {
        this.speed = speed;
        this.armor = armor;
        this.turnSpeed = turnSpeed;
        this.sprite = sprite;
    }
}
