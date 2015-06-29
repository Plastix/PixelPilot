package com.mygdx.pixelpilot.game.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.event.events.ai.AIDeathEvent;
import com.mygdx.pixelpilot.game.plane.Plane;
import net.engio.mbassy.listener.Handler;


public class PlaneMarker extends Marker<Plane> {

    public PlaneMarker(Plane target) {
        super(target);
    }

    @Override
    protected Sprite createSprite() {
        Texture marker = new Texture(Assets.Images.plane_marker);
        Sprite sprite = new Sprite(marker);
        sprite.setSize(30,30);
        sprite.setColor(this.target.getMarkerColor());
        return sprite;
    }

    @Handler
    public void onAIDeath(AIDeathEvent event) {
        if(event.getPlane().equals(this.target)) {
            this.target = null;
            this.remove();
        }
    }
}
