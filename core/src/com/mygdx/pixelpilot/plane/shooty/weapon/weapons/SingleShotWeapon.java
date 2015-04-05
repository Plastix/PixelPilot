package com.mygdx.pixelpilot.plane.shooty.weapon.weapons;

import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.shooty.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.plane.shooty.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.WeaponDefinition;

public class SingleShotWeapon extends Weapon {

    public SingleShotWeapon(Plane owner, WeaponDefinition def) {
        super(def, owner);
    }

    @Override
    protected void buildAndLaunchProjectiles() {
        Projectile projectile = ProjectileFactory.build(projectileType).set(getX(), getY(), owner.getRotation(), owner);
        launch(projectile);
    }
}
