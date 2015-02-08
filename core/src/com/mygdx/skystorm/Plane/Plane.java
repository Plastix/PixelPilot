package com.mygdx.skystorm.plane;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.skystorm.plane.controller.Controller;

public class Plane extends Group {

    PlaneActor planeActor;
    Controller controller;

    public Plane(PlaneActor planeActor, Controller controller) {
        this.planeActor = planeActor;
        this.controller = controller;
        this.addActor(planeActor);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        controller.control(planeActor);
    }

    @Override
    public String toString() {
        return "Plane with " + planeActor + " body and " + controller;
    }
}
