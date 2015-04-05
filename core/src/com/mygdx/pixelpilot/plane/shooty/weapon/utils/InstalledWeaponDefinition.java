package com.mygdx.pixelpilot.plane.shooty.weapon.utils;

import com.mygdx.pixelpilot.plane.Plane;
import com.mygdx.pixelpilot.plane.shooty.weapon.weapons.Weapon;

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
