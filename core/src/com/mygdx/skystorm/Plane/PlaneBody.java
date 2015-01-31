package com.mygdx.skystorm.Plane;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PlaneBody {

    Vector2 position;
    PlaneProperties planeProperties;
    WeaponProperties weaponProperties;
    Sprite sprite;

    public PlaneBody(PlaneProperties planeProperties, WeaponProperties weaponProperties) {
        this.position = new Vector2(0,0);
        this.planeProperties = planeProperties;
        this.weaponProperties = weaponProperties;
        this.sprite = new Sprite(new Texture(planeProperties.spriteHandle));
    }

    public void render(){

    }

    public void update(){

    }

    public void shoot(){

    }
}
