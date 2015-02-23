package com.mygdx.pixelpilot.plane;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.pixelpilot.plane.controller.Controller;

public class Plane extends Group {

    private PlaneActor planeActor;
    private Controller controller;

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

    public PlaneActor getPlaneActor() {
        return planeActor;
    }

    public Controller getController() {
        return controller;
    }
}
