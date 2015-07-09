package com.mygdx.pixelpilot.game.system;

import com.artemis.Entity;
import com.artemis.Manager;
import com.artemis.annotations.Wire;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.game.Plane;

@Wire
public class EntityFactoryManager extends Manager {
    private Plane plane;

    public Entity buildPlane() {
        return plane.setPath(Assets.Images.plane).health(100).position(100, 100).size(50, 50).create();
    }
}
