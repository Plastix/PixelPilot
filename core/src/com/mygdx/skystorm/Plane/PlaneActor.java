package com.mygdx.skystorm.plane;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlaneActor extends Actor {

    Vector2 position;
    PlaneDefinition def;
    Weapon weapon;
    Sprite sprite;

    public PlaneActor(PlaneDefinition def, WeaponDefinition weaponDefinition) {
        this.position = new Vector2(0,0);
        this.def = def;
        this.weapon = new Weapon(weaponDefinition);
        this.sprite = new Sprite(new Texture(def.sprite));
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void act (float delta) {
        super.act(delta);

    }

    public void shoot(){

    }
}
