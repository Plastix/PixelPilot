package com.mygdx.skystorm.plane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PlaneActor extends Image {

    private Vector2 position;
    public Vector2 velocity;
    private PlaneDefinition def;
    private Weapon weapon;

    public PlaneActor(PlaneDefinition def, WeaponDefinition weaponDefinition) {
        super(new Texture(def.spritePath));
        this.position = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        this.def = def;
        this.weapon = new Weapon(weaponDefinition);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        position.add(velocity);
        this.setPosition(position.x, position.y);
    }

    public void shoot(){

    }
}
