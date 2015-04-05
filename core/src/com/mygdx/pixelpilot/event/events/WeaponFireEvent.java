package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.plane.shooty.projectile.projectiles.Projectile;

public class WeaponFireEvent extends GameEvent {
    private Projectile weapon;

    public WeaponFireEvent(Projectile weapon) {
        this.weapon = weapon;
    }

    public Projectile getProjectile(){
        return weapon;
    }
}
