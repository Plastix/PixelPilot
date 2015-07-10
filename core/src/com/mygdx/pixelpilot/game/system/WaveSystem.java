package com.mygdx.pixelpilot.game.system;

import com.artemis.World;
import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.mygdx.pixelpilot.data.GameData;
import com.mygdx.pixelpilot.game.Plane;
import com.mygdx.pixelpilot.game.component.PlaneDefinition;
import com.mygdx.pixelpilot.game.component.Player;

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
                .edit().add(new Player());
    }

    @Override
    protected void processSystem() {

    }
}
