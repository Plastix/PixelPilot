package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.utils.ProjectileFactory;
import com.mygdx.pixelpilot.game.plane.armaments.projectile.projectiles.Projectile;

public class ProjectileSerializer implements ScalarSerializer<Class<? extends Projectile>> {

    @Override
    public String write(Class<? extends Projectile> object) throws YamlException {
        return null;
    }

    @Override
    public Class<? extends Projectile> read(String value) throws YamlException {

        for(Class<? extends Projectile> projectile : ProjectileFactory.getProjectiles()) {
            if(projectile.getSimpleName().equals(value)) {
                return projectile;
            }
        }
        throw new YamlException("No Projectile with name " + value + " found!");
    }
}
