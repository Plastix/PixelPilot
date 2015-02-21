package com.mygdx.skystorm.screen.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.skystorm.data.Resources;
import com.mygdx.skystorm.event.EventHandler;
import com.mygdx.skystorm.event.events.player.PlayerDeathEvent;
import com.mygdx.skystorm.event.events.player.PlayerSpawnEvent;
import com.mygdx.skystorm.plane.Plane;
import com.mygdx.skystorm.plane.PlaneActor;

public class PlaneMarker extends Image {

    private PlaneActor target;
    private PlaneActor player;

    //Player can be null!
    public PlaneMarker(Plane target, Plane player) {
        this.target = target.getPlaneActor();
        if(player != null) {
            this.player = player.getPlaneActor();
        }else{
            this.setVisible(false);
        }

        this.setSize(30,30);
        this.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, Align.center);

        this.setOrigin(Align.center);
        Texture arrow = new Texture(Resources.plane_marker);
        this.setDrawable(new TextureRegionDrawable(new TextureRegion(arrow)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(player != null) {
            Vector2 pos = new Vector2(target.getPosition()).sub(player.getPosition()).nor();
            float angle = (float) (Math.toDegrees(Math.atan2(pos.y, pos.x)));
            this.setRotation(angle);
        }
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnEvent event){
        player = event.getPlane().getPlaneActor();
        this.setVisible(true);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        player = null;
        this.setVisible(false);
    }
}
