package com.mygdx.pixelpilot.game.system;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.game.Plane;
import com.mygdx.pixelpilot.game.camera.GameCamera;
import com.mygdx.pixelpilot.game.component.ParticleEmitter;
import com.mygdx.pixelpilot.game.component.Player;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.ShadowComponent;
import com.mygdx.pixelpilot.game.component.behavior.WanderBehavior;
import com.mygdx.pixelpilot.game.plane.PlaneDefinition;


@Wire
public class WaveSystem extends VoidEntitySystem {

    private World world;
    private Plane plane;

    @Wire
    protected ExtendViewport viewport;

    @Override
    protected void initialize() {
        buildPlayer(GameData.planeDefinitions.get(0));
    }

    private void buildPlayer(PlaneDefinition planeDefinition) {
        Entity entity = plane.health(100)
                .position(100, 100)
                .size(3.5f, 3.5f)
                .rotation(-90)
                .speed(planeDefinition.speed)
                .setPath(planeDefinition.spritePath)
                .minTurnRadius(planeDefinition.minTurnRadius)
                .create()
                .edit().add(new Player())
                .add(new WanderBehavior())
                .add(new ParticleEmitter(Assets.manager.get(Assets.Data.smoke, ParticleEffect.class)))
                .add(new ShadowComponent(0, 20, 1.5f, 1.5f, new Color(0, 0, 0, 0.1f)))
                .getEntity();
        ((GameCamera) (viewport.getCamera())).track(entity.getComponent(Position.class));
    }

    @Override
    protected void processSystem() {

    }
}
