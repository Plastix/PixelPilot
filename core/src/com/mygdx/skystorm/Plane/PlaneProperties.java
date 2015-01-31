package com.mygdx.skystorm.Plane;

import com.badlogic.gdx.files.FileHandle;

public class PlaneProperties {

    int speed;
    int armor;
    int turnSpeed;
    FileHandle spriteHandle;

    public PlaneProperties(int speed, int armor, int turnSpeed, FileHandle spriteHandle) {
        this.speed = speed;
        this.armor = armor;
        this.turnSpeed = turnSpeed;
        this.spriteHandle = spriteHandle;
    }
}
