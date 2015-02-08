package com.mygdx.skystorm.plane.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.skystorm.plane.PlaneActor;

public class PlayerController extends Controller {

    @Override
    public void control(PlaneActor planeBody) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            planeBody.velocity.add(new Vector2(0, 1));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            planeBody.velocity.add(new Vector2(-1, 0));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            planeBody.velocity.add(new Vector2(0, -1));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            planeBody.velocity.add(new Vector2(1, 0));
        }
    }
}
