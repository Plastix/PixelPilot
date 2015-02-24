package com.mygdx.pixelpilot.util;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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

    public static Vector2 vec3d2d(Vector3 vec3) {
        return new Vector2(vec3.x, vec3.y);
    }
    public static Vector3 vec2d3d(Vector2 vec2) {
        return new Vector3(vec2.x, vec2.y, 0);
    }

    public static float map(float val, float inMin, float inMax, float outMin, float outMax){
        return ((val - inMin)/(inMax - inMin)) * (outMax - outMin) + outMin;
    }

    public static Vector2 getBoundsEdgeIntersection(Vector2 target, Vector2 origin, Rectangle bounds) {
        Vector2 topLeft = new Vector2(bounds.getX(), bounds.getY() + bounds.getHeight());
        Vector2 topRight = new Vector2(bounds.getX() + bounds.getWidth(), bounds.getY() + bounds.getHeight());
        Vector2 bottomLeft = new Vector2(bounds.getX(), bounds.getY());
        Vector2 bottomRight = new Vector2(bounds.getX() + bounds.getWidth(), bounds.getY());

        Vector2 intersection = new Vector2();
        // Check intersection with the top edge.
        if (Intersector.intersectSegments(topLeft, topRight, origin, target, intersection)) {
            return intersection;
        }
        // Check intersection with the left edge.
        else if (Intersector.intersectSegments(topLeft, bottomLeft, origin, target, intersection)) {
            return intersection;
        }
        // Check intersection with the right edge.
        else if (Intersector.intersectSegments(topRight, bottomRight, origin, target, intersection)) {
            return intersection;
        }
        // Check intersection with the bottom edge.
        else if (Intersector.intersectSegments(bottomLeft, bottomRight, origin, target, intersection)) {
            return intersection;
        }

        return null;
    }

}
