package com.mygdx.pixelpilot.plane.shooty.weapon.weapons;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.WeaponFireEvent;
import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.WeaponSlot;
import com.mygdx.pixelpilot.plane.shooty.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.WeaponDefinition;

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
        Events.emit(new WeaponFireEvent(p), this);
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

    public float getX() {
        float theta = owner.getRotation();
        float px = slot.position.x;
        float py = slot.position.y;
        float ox = owner.getX();
        float xNew = px * MathUtils.cosDeg(theta) - py * MathUtils.sinDeg(theta);
        return ox + xNew;
    }

    public float getY() {
        float theta = owner.getRotation();
        float px = slot.position.x;
        float py = slot.position.y;
        float oy = owner.getY();
        float yNew = px * MathUtils.sinDeg(theta) + py * MathUtils.cosDeg(theta);
        return oy + yNew;
    }

}
