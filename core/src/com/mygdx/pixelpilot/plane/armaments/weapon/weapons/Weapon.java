package com.mygdx.pixelpilot.plane.armaments.weapon.weapons;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.WeaponFireEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponSlot;
import com.mygdx.pixelpilot.plane.armaments.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponDefinition;

public abstract class Weapon {
    protected Plane owner;
    protected Class<? extends Projectile> projectileType;
    private long timeOfLastShot;
    private long reloadTime;
    private float range;
    WeaponSlot slot;
    WeaponDefinition def;

    public Weapon(WeaponDefinition def, Plane owner) {
        this.projectileType = def.projectileType;
        this.def = def;
        this.range = def.velocity * def.lifespanSeconds; // todo: this calculation is wrong
        this.reloadTime = (int)(def.reloadTime);
        this.timeOfLastShot = System.currentTimeMillis();
        this.owner = owner;
    }

    public void fire(Steerable<Vector2> target) {
        fire();
    }

    public void fire() {
        if (!canFire()) return;
        buildAndLaunchProjectiles();
        timeOfLastShot = System.currentTimeMillis();
    }

    protected abstract void buildAndLaunchProjectiles();

    protected void launch(Projectile p){
        Events.getBus().publish(new WeaponFireEvent(p));
    }

    public boolean canFire() {
        return (System.currentTimeMillis() - timeOfLastShot > reloadTime);
    }

    public float getRange() {
        return range;
    }

    public void mount(WeaponSlot slot) {
        this.slot = slot;
    }

    protected float getX() {
        float theta = owner.getRotation();
        float px = slot.position.x;
        float py = slot.position.y;
        float ox = owner.getX();
        float xNew = px * MathUtils.cosDeg(theta) - py * MathUtils.sinDeg(theta);
        return ox + xNew;
    }

    protected float getY() {
        float theta = owner.getRotation();
        float px = slot.position.x;
        float py = slot.position.y;
        float oy = owner.getY();
        float yNew = px * MathUtils.sinDeg(theta) + py * MathUtils.cosDeg(theta);
        return oy + yNew;
    }

    public float getRotation() {
        float leadFactor = 6;
        float theta = owner.getRotation();
        theta += owner.getAngularVelocity() * MathUtils.radDeg * leadFactor;
        return theta;
    }

}
