package com.mygdx.skystorm.util;

public class Utils {
    public static float randomFloat(float low, float high){
        return (float)(Math.random()*high)+low;
    }

    public static int randomInt(int low, int high){
        return (int)(Math.random()*high)+low;
    }

}
