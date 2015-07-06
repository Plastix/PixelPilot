package com.mygdx.pixelpilot.game;

import com.artemis.World;
import com.artemis.utils.EntityBuilder;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.game.component.*;
import com.mygdx.pixelpilot.game.manager.BackdropManager;
import com.mygdx.pixelpilot.game.system.MovementSystem;
import com.mygdx.pixelpilot.game.system.RenderSystem;

public class Game {

    private World world;

    public Game() {

        world = new World();
        world.setManager(new BackdropManager());
        world.setSystem(new RenderSystem());
        world.setSystem(new MovementSystem());
//        world.setSystem(new LevelCreationSyste());
//        WaveSpawnSystem()
//        LevelLoadSystem()

        world.initialize();

        new EntityBuilder(world).with(
                new Position(Config.NativeView.width / 2, Config.NativeView.height / 2),
                new Velocity(((float) Math.random()), ((float) Math.random())),
                new Renderable(),
                new Rotation(((float) Math.random()) * 360),
                new Size(100, 100),
                new Sprite2D(Assets.Images.plane)
        ).build();

    }

    public void update(float delta) {
        world.setDelta(delta);
        world.process();
    }

}
