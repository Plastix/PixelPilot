package com.mygdx.pixelpilot.plane;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.WeaponFireEvent;
import com.mygdx.pixelpilot.plane.controller.Controller;
import com.mygdx.pixelpilot.util.Utils;

public class Plane extends Actor {

    private PlaneDefinition def;
    private Weapon weapon;
    private Controller controller;

    private Vector2 position;
    private Vector2 linearVelocity;
    private Sprite sprite;


    public Plane(PlaneDefinition def, WeaponDefinition weaponDefinition, Controller controller) {
        this.position = new Vector2(0,0);
        this.def = def;
        this.linearVelocity = new Vector2(1, 1);
        this.linearVelocity.setLength(def.speed);
        this.linearVelocity.setAngle(0);
        this.weapon = new Weapon(weaponDefinition);
        this.sprite = new Sprite(new Texture(def.spritePath));
        this.sprite.setScale(3.5f, 3.5f);
        this.sprite.setRotation(-90);
        this.setSize(sprite.getWidth(), sprite.getHeight());
        this.controller = controller;
        setOrigin(Align.center);
        setRotation(-90);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        controller.control(this);
        this.position.add(linearVelocity);
        this.sprite.setPosition(position.x, position.y);
        this.sprite.setRotation(this.getRotation());

        this.setPosition(position.x, position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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

        // based on http://aviation.stackexchange.com/a/2872
        float minTurnRadiusAng = MathUtils.radDeg * linearVelocity.len() / def.minTurnRadius;

        float turnAng = Utils.map(turnAmount, -1f, 1f, -minTurnRadiusAng, minTurnRadiusAng);
        linearVelocity.rotate(turnAng);
        setRotation(linearVelocity.angle()-90);
    }

    public Color getMarkerColor(){
        return def.markerColor;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public String toString() {
        return "Plane with " + def + " body and " + controller;
    }

}
