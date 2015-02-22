package com.mygdx.pixelpilot.plane.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.pixelpilot.plane.PlaneActor;

public class PlayerController extends Controller {

    float turnAmount = 0.5f;

    @Override
    public void control(PlaneActor planeBody) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            turnAmount = Math.min(turnAmount + 0.05f, 1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            turnAmount = Math.max(turnAmount - 0.05f, 0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            planeBody.turn(turnAmount);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            planeBody.turn(-turnAmount);
        }
    }
}
