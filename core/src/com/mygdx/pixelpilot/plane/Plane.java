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
    private Sprite sprite;
    private Sprite shadow;

    public Plane(final PlaneDefinition def, WeaponDefinition weaponDefinition, Controller controller) {
        this.controller = controller;
        this.def = def;
        this.weapon = new Weapon(weaponDefinition);

        setMaxLinearAcceleration(200);
        setMaxAngularSpeed(5);
        setMaxAngularAcceleration(10);

        this.positionVector = new Vector2(0,0);

        this.linearVelocity = new Vector2(1, 1);
        linearVelocity.setLength(def.speed);
        linearVelocity.setAngle(0);

        this.sprite = new Sprite(new Texture(def.spritePath));
        sprite.setScale(3.5f, 3.5f);
        sprite.setRotation(-90);

        this.shadow = new Sprite(sprite);
        shadow.setColor(new Color(0,0,0,0.1f));
        shadow.setScale(1.5f, 1.5f);

        setSize(sprite.getWidth(), sprite.getHeight());

        setOrigin(Align.center);
        setRotation(-90);
    }

    @Override
    public void act (float delta) {
        super.act(delta);

        // keep the position vector in sync with the actor's position
        // (in case someone calls actor.setPosition)
        this.positionVector.set(getX(), getY());

        this.controller.control(this);
        this.positionVector.add(linearVelocity);
        this.sprite.setPosition(positionVector.x - sprite.getWidth() / 2, positionVector.y - sprite.getHeight() / 2);
        this.shadow.setPosition(positionVector.x - shadow.getWidth() / 2, positionVector.y - 20 - shadow.getHeight() / 2);
        this.sprite.setRotation(this.getRotation());
        this.shadow.setRotation(this.getRotation());

        // keep the actor's position in sync with the position vector
        this.setPosition(positionVector.x, positionVector.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        shadow.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);
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
        setRotation(linearVelocity.angle() - 90);
        angularVel = linearVelocity.angleRad() - prevAngle;
    }

    public void shoot() {
        Events.emit(new WeaponFireEvent(weapon), this);
    }

    public Color getMarkerColor(){
        return def.markerColor;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public float getBoundingRadius() {
        return (sprite.getHeight() + sprite.getWidth()) / 4f;
    }

    @Override
    public float getMaxLinearSpeed() {
        return def.speed;
    }

    @Override
    public String toString() {
        return "Plane with " + def + " body and " + controller;
    }

}
