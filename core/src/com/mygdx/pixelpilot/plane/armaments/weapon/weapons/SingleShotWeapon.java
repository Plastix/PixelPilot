package com.mygdx.pixelpilot.plane.armaments.weapon.weapons;

import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.armaments.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.plane.armaments.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.plane.armaments.weapon.utils.WeaponDefinition;

public class SingleShotWeapon extends Weapon {

    public SingleShotWeapon(Plane owner, WeaponDefinition def) {
        super(def, owner);
    }

    @Override
    protected void buildAndLaunchProjectiles() {
        Projectile projectile = ProjectileFactory.build(projectileType)
                .set(getX(), getY(), getRotation(), getSpeed(), getLifespan(), owner);
        launch(projectile);
    }


}
