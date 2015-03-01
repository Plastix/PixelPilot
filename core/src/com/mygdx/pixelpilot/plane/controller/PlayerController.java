package com.mygdx.pixelpilot.plane.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.util.Utils;

public class PlayerController extends Controller {

    float turnAmount = 0.5f;
    boolean accelerometer;

    public PlayerController() {
        accelerometer = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Override
    public void control(Plane planeBody) {

        if(accelerometer){
            float turn = Utils.map(Gdx.input.getAccelerometerY(), -5, 5, -1, 1);
            planeBody.turn(-turn);
        }

        //Desktop Controls
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
