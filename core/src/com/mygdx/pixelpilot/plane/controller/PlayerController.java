package com.mygdx.pixelpilot.plane.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.pixelpilot.plane.PlaneActor;

public class PlayerController extends Controller {

    float turnSpeed = 1f;

    @Override
    public void control(PlaneActor planeBody) {
        
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            turnSpeed += 0.2f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            turnSpeed = Math.max(turnSpeed-0.2f, 1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            planeBody.turn(turnSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            planeBody.turn(-turnSpeed);
        }
    }
}
