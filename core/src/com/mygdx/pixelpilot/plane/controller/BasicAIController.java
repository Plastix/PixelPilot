package com.mygdx.pixelpilot.plane.controller;

import com.mygdx.pixelpilot.plane.PlaneActor;

public class BasicAIController extends AIController {

    @Override
    public void control(PlaneActor planeBody) {
        //Testing
        planeBody.turn(1f);
    }
}
