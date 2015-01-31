package com.mygdx.skystorm.Plane;

import com.badlogic.gdx.math.Vector2;

public class PlaneBody {

    Vector2 position;
    Properties properties;
    Weapon weapon;

    public PlaneBody(Properties properties, Weapon weapon) {
        this.position = new Vector2(0,0);
        this.properties = properties;
        this.weapon = weapon;
    }

    public void render(){

    }

    public void update(){

    }

    public void shoot(){

    }
}
