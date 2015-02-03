package com.mygdx.skystorm.plane;


public class PlaneDefinition {

    public String name;
    public int speed;
    public int armor;
    public int turnSpeed;
    public String spritePath;

    @Override
    public String toString(){
        return String.format("name: %s, speed: %s, armor: %s, turnSpeed: %s, spritePath: %s",
                name, speed, armor, turnSpeed, spritePath);
    }

}
