package com.mygdx.pixelpilot.plane.armaments.projectile.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.ProjectileExpirationEvent;
import com.mygdx.pixelpilot.event.events.game.GamePauseEvent;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.plane.SteerableActor;
import net.engio.mbassy.listener.Handler;

/**
 * The abstract projectile manages lifespan and returns
 * itself to the pool when it expires.
 *
 * @param <T> The type of the subclass
 */
public abstract class Projectile<T extends Projectile> extends SteerableActor {
    protected Actor owner;
    protected Sprite sprite;
    protected float velocityX, velocityY;
    protected float speed;
    private Pool<T> pool;
    private long spawnTime;
    private float lifespan;

    public Projectile(Pool<T> pool, Sprite sprite, float lifespan, float speed) {
        this.sprite = sprite;
        this.lifespan = lifespan;
        this.speed = speed;
        this.pool = pool;
        this.spawnTime = System.currentTimeMillis();
    }

    public Projectile set(float x, float y, float rotation, float speed, float lifespan, Actor owner) {
        setPosition(x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
        sprite.setRotation(rotation);
        sprite.setScale(2f);
        sprite.setPosition(getX(), getY());
        this.owner = owner;
        this.speed = speed;
        this.lifespan = lifespan;
        this.velocityX = MathUtils.cosDeg(rotation + 90) * speed;
        this.velocityY = MathUtils.sinDeg(rotation + 90) * speed;
        this.spawnTime = System.currentTimeMillis();
        return this;
    }

    /**
     * Must override to free the current projectile to the pool
     */
    abstract void free(Pool<T> p);

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX() + velocityX, getY() + velocityY);
        sprite.setPosition(getX(), getY());
        if (System.currentTimeMillis() - spawnTime > lifespan) {
            Events.getBus().publish(new ProjectileExpirationEvent(this));
            free(pool);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }
}
