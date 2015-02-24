package com.mygdx.pixelpilot.plane;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.WeaponFireEvent;
import com.mygdx.pixelpilot.util.Utils;

public class PlaneActor extends Image {

    private Vector2 position;
    private Vector2 linearVelocity;
    private PlaneDefinition def;
    private Weapon weapon;

    public PlaneActor(PlaneDefinition def, WeaponDefinition weaponDefinition) {
        super(new Texture(def.spritePath));
        this.position = new Vector2(0,0);
        this.def = def;
        this.linearVelocity = new Vector2(1, 1);
        this.linearVelocity.setLength(def.speed);
        this.linearVelocity.setAngle(0);
        this.weapon = new Weapon(weaponDefinition);
        setScale(3f, 3f);
        setOrigin(Align.center);
        setRotation(-90);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        this.position.add(linearVelocity);
        this.setPosition(position.x, position.y);
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

    public Vector2 getPosition() {
        return position;
    }

    public Color getMarkerColor(){
        return def.markerColor;
    }
}
