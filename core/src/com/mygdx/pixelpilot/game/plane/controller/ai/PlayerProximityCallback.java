package com.mygdx.pixelpilot.game.plane.controller.ai;

import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.game.plane.OldPlane;
import com.mygdx.pixelpilot.game.plane.controller.AIController;
import com.mygdx.pixelpilot.game.plane.controller.PlayerController;

public class PlayerProximityCallback implements Proximity.ProximityCallback<Vector2>{
    AIController controller;

    public PlayerProximityCallback(AIController controller) {
        this.controller = controller;
    }

    @Override
    public boolean reportNeighbor(Steerable<Vector2> neighbor) {
        if (neighbor instanceof OldPlane && ((OldPlane) neighbor).getController() instanceof PlayerController) {
            controller.setTarget(neighbor);
            return true;
        }
        return false;

    }
}
