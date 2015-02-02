package com.mygdx.skystorm.plane;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PlaneBody {

    Vector2 position;
    PlaneProperty planeProperty;
    WeaponProperty weaponProperty;
    Sprite sprite;

    public PlaneBody(PlaneProperty planeProperty, WeaponProperty weaponProperty) {
        this.position = new Vector2(0,0);
        this.planeProperty = planeProperty;
        this.weaponProperty = weaponProperty;
//        this.sprite = new Sprite(new Texture(planeProperties.spriteHandle));
    }

    public void render(){

    }

    public void update(){

    }

    public void shoot(){

    }
}
