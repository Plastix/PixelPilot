package com.mygdx.pixelpilot.game.plane.armaments.weapon.weapons;

import com.mygdx.pixelpilot.game.plane.OldPlane;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponDefinition;

public class SingleShotWeapon extends Weapon {

    public SingleShotWeapon(OldPlane owner, WeaponDefinition def) {
        super(def, owner);
    }

    @Override
    protected void buildAndLaunchProjectiles() {
        Projectile projectile = ProjectileFactory.build(projectileType)
                .set(getX(), getY(), getRotation(), getSpeed(), getLifespan(), owner);
        launch(projectile);
    }


}
