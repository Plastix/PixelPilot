package com.mygdx.skystorm.plane.controller;

import com.mygdx.skystorm.plane.PlaneActor;

public class BasicAIController extends AIController {

    @Override
    public void control(PlaneActor planeBody) {
        //Testing
        planeBody.turn(1f);
    }
}
