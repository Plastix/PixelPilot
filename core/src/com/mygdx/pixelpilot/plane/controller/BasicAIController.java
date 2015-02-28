package com.mygdx.pixelpilot.plane.controller;

import com.mygdx.pixelpilot.plane.Plane;

public class BasicAIController extends AIController {

    @Override
    public void control(Plane planeBody) {
        //Testing
        planeBody.turn(1f);
    }
}
