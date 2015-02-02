package com.mygdx.skystorm.plane;

import com.mygdx.skystorm.plane.controller.Controller;

public class Plane {

    PlaneBody planeBody;
    Controller controller;

    public Plane(PlaneBody planeBody, Controller controller) {
        this.planeBody = planeBody;
        this.controller = controller;
    }

    public void update(){
        planeBody.update();
        controller.control(planeBody);
    }
}
