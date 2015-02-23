package com.mygdx.pixelpilot.plane;


public class PlaneDefinition {

    public String name;
    public int speed;
    public int armor;
    public int turnRadius;
    public String spritePath;

    @Override
    public String toString(){
        return String.format("name: %s, speed: %s, armor: %s, turnRadius: %s, spritePath: %s",
                name, speed, armor, turnRadius, spritePath);
    }

}
