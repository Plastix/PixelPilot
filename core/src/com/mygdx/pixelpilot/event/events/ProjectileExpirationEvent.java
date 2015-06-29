package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.game.plane.armaments.projectile.projectiles.Projectile;

public class ProjectileExpirationEvent extends GameEvent{
    private Projectile projectile;

    public ProjectileExpirationEvent(Projectile projectile) {
        this.projectile = projectile;
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
