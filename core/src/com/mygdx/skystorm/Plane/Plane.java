package com.mygdx.skystorm.Plane;

import com.mygdx.skystorm.Plane.Controller.Controller;

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
