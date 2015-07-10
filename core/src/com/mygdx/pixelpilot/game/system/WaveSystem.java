package com.mygdx.pixelpilot.game.system;

import com.artemis.World;
import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.game.Plane;
import com.mygdx.pixelpilot.game.component.ParticleEmitter;
import com.mygdx.pixelpilot.game.component.Player;
import com.mygdx.pixelpilot.game.plane.PlaneDefinition;

@Wire
public class WaveSystem extends VoidEntitySystem {

    private World world;
    private Plane plane;

    @Override
    protected void initialize() {
        buildPlayer(GameData.planeDefinitions.get(0));
    }

    private void buildPlayer(PlaneDefinition planeDefinition) {
        plane.health(100)
                .position(100, 100)
                .size(3.5f, 3.5f)
                .rotation(-90)
                .speed(planeDefinition.speed)
                .setPath(planeDefinition.spritePath)
                .minTurnRadius(planeDefinition.minTurnRadius)
                .create()
                .edit().add(new Player())
                .add(new ParticleEmitter(Assets.manager.get(Assets.Data.smoke, ParticleEffect.class)));
    }

    @Override
    protected void processSystem() {

    }
}
