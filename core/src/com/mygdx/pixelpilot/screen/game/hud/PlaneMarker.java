package com.mygdx.pixelpilot.screen.game.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.event.EventHandler;
import com.mygdx.pixelpilot.event.Listener;
import com.mygdx.pixelpilot.event.events.ai.AIDeathEvent;
import com.mygdx.pixelpilot.plane.Plane;


public class PlaneMarker extends Marker<Plane> implements Listener {

    public PlaneMarker(Plane target) {
        super(target);
    }

    @Override
    protected Sprite createSprite() {
        Texture marker = new Texture(Assets.image.plane_marker);
        Sprite sprite = new Sprite(marker);
        sprite.setSize(30,30);
        sprite.setColor(this.target.getMarkerColor());
        return sprite;
    }

    @EventHandler
    public void onAIDeath(AIDeathEvent event) {
        if(event.getPlane().equals(this.target)) {
            this.target = null;
            this.remove();
        }
    }
}
