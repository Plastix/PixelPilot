package com.mygdx.pixelpilot.plane.armaments.weapon.utils;

import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.armaments.weapon.weapons.MultiShotWeapon;
import com.mygdx.pixelpilot.plane.armaments.weapon.weapons.SingleShotWeapon;
import com.mygdx.pixelpilot.plane.armaments.weapon.weapons.Weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeaponFactory {

    private static Map<Class<? extends Weapon>, WeaponProvider> providerMap
            = new HashMap<Class<? extends Weapon>, WeaponProvider>();

    public static void registerWeapons(){
        providerMap.put(SingleShotWeapon.class, new WeaponProvider<SingleShotWeapon>() {
            @Override
            public SingleShotWeapon create(Plane owner, WeaponDefinition def) {
                return new SingleShotWeapon(owner, def);
            }
        });

        providerMap.put(MultiShotWeapon.class, new WeaponProvider<MultiShotWeapon>() {
            @Override
            public MultiShotWeapon create(Plane owner, WeaponDefinition def) {
                return new MultiShotWeapon(owner, def);
            }
        });
    }

    public static Weapon build(Plane owner, WeaponDefinition def) {
        if(isValid(def.weaponType.type)) {
            return providerMap.get(def.weaponType.type).obtain(owner, def);
        } else {
            throw new RuntimeException("No weapon found ["
                    + def.weaponType.type
                    + "] check that the weapon type is the same as the class name");
        }
    }

    private static boolean isValid(Class<? extends Weapon> weapon) {
        return providerMap.containsKey(weapon);
    }

    public static Set<Class<? extends Weapon>> getWeapons() {
        return providerMap.keySet();
    }
}
