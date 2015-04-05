package com.mygdx.pixelpilot.plane.shooty.weapon.weapons;

import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.shooty.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.plane.shooty.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.WeaponDefinition;
import com.mygdx.pixelpilot.util.Utils;

public class MultiShotWeapon extends Weapon {

    int numShots = 6;

    public MultiShotWeapon(Plane owner, WeaponDefinition def) {
        super(def, owner);
        if(def.weaponProperties.containsKey("numShots")){
            try {
                numShots = Integer.parseInt((String)def.weaponProperties.get("numShots"));
            } catch (NumberFormatException e) {
                System.out.println("Weapon property \"numShots\" must be either null or a valid integer");
                e.printStackTrace();

            }
        }
    }

    @Override
    protected void buildAndLaunchProjectiles() {
        for (int i = 0; i < numShots; i++) {
            Projectile projectile = ProjectileFactory.build(projectileType)
                    .set(getX(), getY(), owner.getRotation()
                            + Utils.map(i, 0, numShots - 1, -15, 15), owner);
            launch(projectile);
        }
    }
}
