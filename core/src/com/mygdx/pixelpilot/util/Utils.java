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

    public static float map(float val, float inMin, float inMax, float outMin, float outMax){
        return ((val - inMin)/(inMax - inMin)) * (outMax - outMin) + outMin;
    }

    /**
     * Finds the intersection point between a line and a rectangle
     * @param vec The intersection point will be stored here if it exists, otherwise this will be null
     * @param targetX The x position of the first point on the line
     * @param targetY The y position of the first point on the line
     * @param originX The x position of the second point on the line
     * @param originY The y position of the second point on the line
     * @param rectX The x position of the rectangle
     * @param rectY The y position of the rectangle
     * @param rectW The width of the rectangle
     * @param rectH The height of the rectangle
     * @return true if there is an intersection point, otherwise false
     */
    public static boolean intersectLineRect(Vector2 vec, float targetX, float targetY, float originX, float originY, float rectX, float rectY, float rectW, float rectH) {

        return
                // Check intersection with the top edge.
                Intersector.intersectSegments(rectX, rectY+rectH, rectX + rectW, rectY + rectH, originX, originY, targetX, targetY, vec)
                // Check intersection with the left edge.
                || Intersector.intersectSegments(rectX, rectY+rectH, rectX, rectY, originX, originY, targetX, targetY, vec)
                // Check intersection with the right edge.
                || Intersector.intersectSegments(rectX + rectW, rectY + rectH, rectX + rectW, rectY, originX, originY, targetX, targetY, vec)
                // Check intersection with the bottom edge.
                || Intersector.intersectSegments(rectX, rectY, rectX+rectW, rectY, originX, originY, targetX, targetY, vec);
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
