package com.mygdx.pixelpilot.game.plane.armaments.weapon.utils;

import com.mygdx.pixelpilot.game.plane.Plane;
import com.mygdx.pixelpilot.game.plane.armaments.weapon.weapons.Weapon;

/**
 * A pairing of WeaponDefinition and WeaponSlot
 */
public class InstalledWeaponDefinition {
    public WeaponDefinition weapon;
    public WeaponSlot slot;

    public void mount(WeaponSlot slot) {
        this.slot = slot;
    }

    public Weapon create(Plane owner){
        Weapon weapon = WeaponFactory.build(owner, this.weapon);
        weapon.mount(slot);
        return weapon;
    }
    @Override
    public String toString() {
        return "(wep:"+ weapon +", slot:"+slot+")";
    }
}
