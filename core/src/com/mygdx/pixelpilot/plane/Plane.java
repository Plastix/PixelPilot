package com.mygdx.pixelpilot.plane;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.WeaponFireEvent;
import com.mygdx.pixelpilot.plane.controller.Controller;
import com.mygdx.pixelpilot.util.Utils;

public class Plane extends SteerableActor {
    private PlaneDefinition def;
    private Weapon weapon;
    private Controller controller;

    private Vector2 position;
    private Vector2 linearVelocity;
    private Sprite sprite;
    private Sprite shadow;

    private boolean tagged;
    private float angularVel;
    private float maxLinearAcceleration = 200;
    private float maxAngularSpeed = 5;
    private float maxAngularAcceleration = 10;


    public Plane(PlaneDefinition def, WeaponDefinition weaponDefinition, Controller controller) {
        this.position = new Vector2(0,0);
        this.controller = controller;
        this.def = def;
        this.linearVelocity = new Vector2(1, 1);
        this.linearVelocity.setLength(def.speed);
        this.linearVelocity.setAngle(0);
        this.weapon = new Weapon(weaponDefinition);
        this.sprite = new Sprite(new Texture(def.spritePath));
        this.sprite.setScale(3.5f, 3.5f);
        this.sprite.setRotation(-90);
        this.setSize(sprite.getWidth(), sprite.getHeight());
        this.shadow = new Sprite(sprite);
        this.shadow.setColor(new Color(0,0,0,0.1f));
        this.shadow.setScale(1.5f, 1.5f);
        setOrigin(Align.center);
        setRotation(-90);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        this.position.set(getX(), getY());
        this.controller.control(this);
        this.position.add(linearVelocity);
        this.sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
        this.shadow.setPosition(position.x - shadow.getWidth() / 2, position.y - 20 - shadow.getHeight() / 2);
        this.sprite.setRotation(this.getRotation());
        this.shadow.setRotation(this.getRotation());
        this.setPosition(position.x, position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        shadow.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);
    }

    public void shoot() {
        Events.emit(new WeaponFireEvent(weapon), this);
    }

    /**
     * Causes the plane to change its heading
     * @param turnAmount amount to turn this frame, expects to be in range [-1, 1]
     */
    public void turn(float turnAmount) {
        float prevAngle = linearVelocity.angleRad();
        // based on http://aviation.stackexchange.com/a/2872
        float minTurnRadiusAng = MathUtils.radDeg * linearVelocity.len() / def.minTurnRadius;

        float turnAng = Utils.map(turnAmount, -1f, 1f, -minTurnRadiusAng, minTurnRadiusAng);
        linearVelocity.rotate(turnAng);
        setRotation(linearVelocity.angle()-90);
        angularVel = linearVelocity.angleRad() - prevAngle;
    }

    public Color getMarkerColor(){
        return def.markerColor;
    }

    @Override
    public Vector2 getPosition() {
        return position;
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

    @Override
    public float getBoundingRadius() {
        return (sprite.getHeight() + sprite.getWidth()) / 4f;
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
        return (float)Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float)Math.sin(angle);
        outVector.y = (float)Math.cos(angle);
        return outVector;
    }

    @Override
    public float getMaxLinearSpeed() {
        return def.speed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        // this is fixed
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

    public Controller getController() {
        return controller;
    }

    @Override
    public String toString() {
        return "Plane with " + def + " body and " + controller;
    }

}
