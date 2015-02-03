package com.mygdx.skystorm.plane.controller;

import com.mygdx.skystorm.plane.PlaneActor;

public abstract class Controller {

    public abstract void control(PlaneActor planeBody);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
