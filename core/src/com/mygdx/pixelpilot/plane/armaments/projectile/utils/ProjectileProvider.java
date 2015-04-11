package com.mygdx.pixelpilot.plane.armaments.projectile.utils;

import com.badlogic.gdx.utils.Pool;
import com.mygdx.pixelpilot.plane.armaments.projectile.projectiles.Projectile;

/**
 * Manages a pool of Projectiles of type T
 * Basically a wrapper around a pool
 * Provides a method for obtaining projectiles from the pool
 *
 * @param <T> The type of the projectile to be provided
 */
public abstract class ProjectileProvider<T extends Projectile> {
    Pool<T> pool = new Pool<T>() {
        @Override
        protected T newObject() {
            return create();
        }
    };

    public T obtain() {
        return pool.obtain();
    }

    /**
     * Returns a new instance of the projectile
     *
     * @return A new projectile of type T
     */
    protected abstract T create();
}
