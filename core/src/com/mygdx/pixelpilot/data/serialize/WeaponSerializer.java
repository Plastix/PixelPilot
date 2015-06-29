package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.weapons.Weapon;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponFactory;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.utils.WeaponTypeContainer;

public class WeaponSerializer implements ScalarSerializer<WeaponTypeContainer> {
    @Override
    public String write(WeaponTypeContainer object) throws YamlException {
        return null;
    }

    @Override
    public WeaponTypeContainer read(String value) throws YamlException {
        for(Class<? extends Weapon> weapon : WeaponFactory.getWeapons()) {
            if(weapon.getSimpleName().equals(value)) {
                WeaponTypeContainer container = new WeaponTypeContainer();
                container.type = weapon;
                return container;
            }
        }
        throw new YamlException("No weapon with name " + value + " found!");
    }
}
