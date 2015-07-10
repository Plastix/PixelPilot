package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Velocity extends Component {

    public Vector2 vector;

    public Velocity() {
        this(1, 0);
    }

    public Velocity(float x, float y) {
        vector = new Vector2(x, y);
    }

    public void setSpeed(float speed){
        vector.setLength(speed);
    }

    public float getX() {
        return vector.x;
    }

    public float getY() {
        return vector.y;
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "vector=" + vector +
                '}';
    }
}
