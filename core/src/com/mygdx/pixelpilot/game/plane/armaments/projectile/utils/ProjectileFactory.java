package com.mygdx.pixelpilot.game.plane.armaments.projectile.utils;

import com.mygdx.pixelpilot.game.plane.armaments.projectile.projectiles.TrackingBullet;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.projectiles.Projectile;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.projectiles.StandardBullet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProjectileFactory {

    static Map<Class<? extends Projectile>, ProjectileProvider> providerMap = new HashMap<Class<? extends Projectile>, ProjectileProvider>();

    /**
     * Each individual type of projectile must be registered in this method
     * before it can be referenced in the YAML configuration file
     */
    public static void registerProjectiles() {

        providerMap.put(StandardBullet.class, new ProjectileProvider<StandardBullet>() {
            @Override
            protected StandardBullet create() {
                return new StandardBullet(pool);
            }
        });

        providerMap.put(TrackingBullet.class, new ProjectileProvider<TrackingBullet>() {
            @Override
            protected TrackingBullet create() {
                return new TrackingBullet(pool);
            }
        });
    }

    public static Set<Class<? extends Projectile>> getProjectiles() {
        return providerMap.keySet();
    }

    public static Projectile build(Class<? extends Projectile> projectile) {
        // if it exists?
        if (isValid(projectile)) {
            return providerMap.get(projectile).obtain();
        } else {
            throw new RuntimeException("Projectile type "
                    + projectile
                    + " not found. Make sure it is spelled correctly and registered in the providerMap in ProjectileFactory");
        }
    }

    private static boolean isValid(Class<? extends Projectile> projectile) {
        return providerMap.containsKey(projectile);
    }
}
