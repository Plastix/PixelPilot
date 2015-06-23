package com.mygdx.pixelpilot.plane.armaments.projectile.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.pixelpilot.data.Assets;

public class StandardBullet extends Projectile<StandardBullet> {

    public StandardBullet(Pool<StandardBullet> pool) {
//        Texture tex;
//        if(Assets.manager.isLoaded("image/projectiles/1.png", Texture.class)){
//            tex = Assets.manager.get("image/projectiles/1.png", Texture.class);
//        }else{
//            tex = new Texture("image/projectiles/1.png");
//        }

        super(pool,
                new Sprite(new Texture("image/projectiles/1.png")),
                1500, // lifespan
                15);  // speed
        setOrigin(Align.center);
    }

    @Override
    void free(Pool<StandardBullet> p) {
        p.free(this);
    }
}
