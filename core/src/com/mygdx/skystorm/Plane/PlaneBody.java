package com.mygdx.skystorm.Plane;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PlaneBody {

    Vector2 position;
    Properties properties;
    Weapon weapon;
    Sprite sprite;

    public PlaneBody(Properties properties, Weapon weapon) {
        this.position = new Vector2(0,0);
        this.properties = properties;
        this.weapon = weapon;
        this.sprite = new Sprite(new Texture(properties.spriteHandle));
    }

    public void render(){

    }

    public void update(){

    }

    public void shoot(){

    }
}
