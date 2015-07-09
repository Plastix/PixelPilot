package com.mygdx.pixelpilot.game;

import com.artemis.World;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.game.system.MovementSystem;
import com.mygdx.pixelpilot.game.system.PlayerInputSystem;
import com.mygdx.pixelpilot.game.system.RenderSystem;
import com.mygdx.pixelpilot.game.system.WaveSystem;

public class Game {

    private World world;
    private Backdrop backdrop;
    public Game() {

        world = new World();
        world.setSystem(new RenderSystem());
        world.setSystem(new MovementSystem());
        world.setSystem(new PlayerInputSystem());
        world.setSystem(new WaveSystem());
//        world.setSystem(new LevelCreationSyste());
//        WaveSpawnSystem()
//        LevelLoadSystem()

        world.initialize();


//        new PlaneFactory().build(GameData.planeDefinitions.get(0), new ArrayList<InstalledWeaponDefinition>(), null);

//        new EntityBuilder(world).with(
//                new Position(Config.NativeView.width / 2, Config.NativeView.height / 2),
//                new Velocity(((float) Math.random()), ((float) Math.random())),
//                new Renderable(),
//                new Rotation(((float) Math.random()) * 360),
//                new Size(100, 100),
//                new Sprite2D(Assets.Images.plane)
//        ).build();

    }

    public void update(float delta) {
        world.setDelta(delta);
        world.process();
    }

}
