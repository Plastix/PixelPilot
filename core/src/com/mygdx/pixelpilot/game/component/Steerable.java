package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * We have to keep the position and velocity in sync with their corresponding components
 * That's done in the SteerableSystem
 */
public class Steerable extends Component implements com.badlogic.gdx.ai.steer.Steerable<Vector2> {
    protected Vector2 positionVector = new Vector2();
    protected Vector2 linearVelocity = new Vector2();
    protected float angularVel;
    private float maxLinearAcceleration = 1;
    private float maxLinearSpeed = 1;
    private float maxAngularAcceleration = 1;
    private float maxAngularSpeed = 1;
    protected boolean tagged;

    // Steerable implementation
    @Override
    public Vector2 getPosition() {
        return positionVector;
    }

    @Override
    public float getOrientation() {
        return linearVelocity.angleRad() - (MathUtils.degRad * 90);
    }

    @Override
    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    @Override
    public float getAngularVelocity() {
        return angularVel;
    }

    public void setAngularVelocity(float angularVel) {
        this.angularVel = angularVel;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    @Override
    public Vector2 newVector() {
        return new Vector2();
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float) Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float) Math.sin(angle);
        outVector.y = (float) Math.cos(angle);
        return outVector;
    }

    // Limiter implementation
    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public float getBoundingRadius() {
//        return  (getHeight() + getWidth()) / 4f;
        return 1; // todo: steerable needs a bounding radius thingy
    }
}
