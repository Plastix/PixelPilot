package com.mygdx.pixelpilot.plane.shooty.projectile.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Pool;

// todo: implement
public class TrackingBullet extends Projectile<TrackingBullet> {

    public TrackingBullet(Pool<TrackingBullet> pool) {
        super(pool,
                new Sprite(new Texture("image/projectiles/2.png")),
                1500,
                15);
        setOrigin(Align.center);
    }

    @Override
    void free(Pool<TrackingBullet> p) {
        p.free(this);
    }

}
