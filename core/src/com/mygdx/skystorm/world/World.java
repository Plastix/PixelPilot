package com.mygdx.skystorm.world;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.skystorm.plane.Plane;
import com.mygdx.skystorm.world.background.Backdrop;

import java.util.List;

public class World extends Group {

    Backdrop backdrop;
    List<Cloud> clouds;
    List<Plane> planes;


    public World(Backdrop backdrop, List<Cloud> clouds) {
        this.backdrop = backdrop;
        this.clouds = clouds;
    }

    public void spawnPlane(Plane plane){
        this.addActor(plane);
        this.planes.add(plane);
    }


}
