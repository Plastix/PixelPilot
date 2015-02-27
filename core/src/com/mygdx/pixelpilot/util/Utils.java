package com.mygdx.pixelpilot.util;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utils {

    /**
     * Returns a Vector2 which is perpendicular to the given vector in the given direction
     * @param v The vector to be rotated
     * @param dir The direction (positive or negative)
     * @return A normalized perpendicular vector
     */
    public static Vector2 perpendicularVector(Vector2 v, int dir){
        return v.cpy()
                .rotate90(dir)
                .nor();
    }

    /**
     * Returns a Vector2 corresponding to the position of the given actor
     * @param target The actor to create a position vector from
     * @return A Vector2 containing the x and y coordinates of the actor
     */
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

    public static boolean intersectLineRect(Vector2 target, Vector2 origin, Rectangle bounds, Vector2 intersectionPoint) {
        return intersectLineRect(target, origin, bounds, intersectionPoint, null);
    }

    public static boolean intersectLineRect(Vector2 target, Vector2 origin, Rectangle bounds, Vector2 intersectionPoint, Vector2 normal) {
        Vector2 topLeft = new Vector2(bounds.getX(), bounds.getY() + bounds.getHeight());
        Vector2 topRight = new Vector2(bounds.getX() + bounds.getWidth(), bounds.getY() + bounds.getHeight());
        Vector2 bottomLeft = new Vector2(bounds.getX(), bounds.getY());
        Vector2 bottomRight = new Vector2(bounds.getX() + bounds.getWidth(), bounds.getY());

        // Check intersection with the top edge.
        if (Intersector.intersectSegments(topLeft, topRight, origin, target, intersectionPoint)) {
            if(normal != null) normal.set(calculateNormal(origin, intersectionPoint, topLeft, topRight));
            return true;
        }
        // Check intersection with the left edge.
        else if (Intersector.intersectSegments(topLeft, bottomLeft, origin, target, intersectionPoint)) {
            if(normal != null) normal.set(calculateNormal(origin, intersectionPoint, topLeft, bottomLeft));
            return true;
        }
        // Check intersection with the right edge.
        else if (Intersector.intersectSegments(topRight, bottomRight, origin, target, intersectionPoint)) {
            if(normal != null) normal.set(calculateNormal(origin, intersectionPoint, topRight, bottomRight));
            return true;
        }
        // Check intersection with the bottom edge.
        else if (Intersector.intersectSegments(bottomLeft, bottomRight, origin, target, intersectionPoint)) {
            if(normal != null) normal.set(calculateNormal(origin, intersectionPoint, bottomLeft, bottomRight));
            return true;
        }

        return false;
    }

    public static Vector2 calculateNormal(Vector2 origin, Vector2 intersectionPoint, Vector2 linePoint1, Vector2 linePoint2) {
        int side = Intersector.pointLineSide(linePoint1, linePoint2, origin);
        float dx = linePoint2.x - linePoint1.x;
        float dy = linePoint2.y - linePoint1.y;
        if(side > 0) {
            return new Vector2(-dy, dx);
        } else if(side < 0) {
            return new Vector2(dy, -dx);
        } else {
            return intersectionPoint;
        }
    }

    /**
     * Returns true if the given point is inside the specified rectangle
     * @param px The x coordinate of the point
     * @param py The y coordinate of the point
     * @param rx The x coordinate of the corner of the rectangle
     * @param ry The y coordinate of the corner of the rectangle
     * @param rw The width of the rectangle
     * @param rh The height of the rectangle
     * @return True if the point is inside the bounds of the rectangle
     */
    public static boolean pointInRect(float px, float py, float rx, float ry, float rw, float rh) {
        return (px < rx + rw && px > rx && py > ry && py < ry + rh );
    }

}
