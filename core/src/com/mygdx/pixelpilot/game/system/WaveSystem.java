package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.Bag;
import com.artemis.utils.EntityBuilder;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.game.Plane;
import com.mygdx.pixelpilot.game.component.PlaneDefinition;
import com.mygdx.pixelpilot.game.component.Player;
import com.mygdx.pixelpilot.game.component.Rotation;
import com.mygdx.pixelpilot.game.component.Velocity;

@Wire
public class WaveSystem extends VoidEntitySystem {
    private World world;
    private Plane plane;

    @Override
    protected void initialize() {
        Entity e = buildPlayer(GameData.planeDefinitions.get(0));
    }

    private Entity buildPlayer(PlaneDefinition planeDefinition) {
        Entity e =  plane.health(100)
                .position(100, 100)
                .size(50, 50)
                .speed(planeDefinition.speed)
                .setPath(planeDefinition.spritePath)
                .create().edit().remove(PlaneDefinition.class).add(planeDefinition).getEntity();
        e.edit().add(new Player());
        return e;
    }

    @Override
    protected void processSystem() {

    }
}
