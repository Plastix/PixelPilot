package com.mygdx.pixelpilot.plane;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
        this.position = new Vector2(200,200);
        this.def = def;
        this.linearVelocity = new Vector2(1, 1); /* can't be zero because then scaling won't work */
        linearVelocity.setLength(def.speed);
        linearVelocity.setAngle(0);
        this.weapon = new Weapon(weaponDefinition);
        setScale(3f, 3f);
        setOrigin(Align.center);
        setRotation(-90);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        position.add(linearVelocity);
        this.setPosition(position.x, position.y);
        
        /*Reset things back to their base values*/
        linearVelocity.nor().scl(def.speed);
    }

    public void shoot() {
        Events.emit(new WeaponFireEvent(weapon), this);
    }

    /* TODO: clamp linearVelocity to ensure it stays above 0*/
    public void turn(float omega) {

        linearVelocity.scl(Math.abs(omega));

        float speed = linearVelocity.len();

        Vector2 radialAcceleration = Utils.perpendicularVector(linearVelocity, omega > 0 ? 1 : -1)
                .scl((speed * speed) / def.turnRadius);
        linearVelocity.add(radialAcceleration);

        setRotation(linearVelocity.angle()-90);
    }

    ShapeRenderer shapeDebugger = new ShapeRenderer();

    @Override
    public void draw(Batch b, float parentAlpha){
        super.draw(b, parentAlpha);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Color getMarkerColor(){
        return def.markerColor;
    }
}
