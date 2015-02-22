package com.mygdx.pixelpilot.plane;


import com.badlogic.gdx.graphics.Color;

public class PlaneDefinition {

    public String name;
    public int speed;
    public int armor;
    public int minTurnRadius;
    public String spritePath;
    public Color markerColor;

    @Override
    public String toString(){
        return String.format("name: %s, speed: %s, armor: %s, minTurnRadius: %s, spritePath: %s",
                name, speed, armor, minTurnRadius, spritePath);
    }

}
