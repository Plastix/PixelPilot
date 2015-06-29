package com.mygdx.pixelpilot.game;

import com.artemis.World;

public class Game {

    private World world;

    public Game() {
        world = new World();

        //Add stuff here

        world.initialize();
    }

    public void update(float delta){
        world.setDelta(delta);
        world.process();
    }

}
