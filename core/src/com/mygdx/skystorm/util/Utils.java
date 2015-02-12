package com.mygdx.skystorm.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utils {
    public static float randomFloat(float low, float high){
        return (float)(Math.random()*high)+low;
    }

    public static int randomInt(int low, int high){
        return (int)(Math.random()*high)+low;
    }

    public static Vector2 perpendicularVector(Vector2 v, int dir){
        return v.cpy()
                .rotate90(dir)
                .nor();
    }

    public static Vector2 positionVector(Actor target) {
        return new Vector2(target.getX(), target.getY());
    }

}
