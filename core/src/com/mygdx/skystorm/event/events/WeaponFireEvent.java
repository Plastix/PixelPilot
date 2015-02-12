package com.mygdx.skystorm.event.events;

import com.mygdx.skystorm.plane.Weapon;

public class WeaponFireEvent extends GameEvent {
    private Weapon weapon;

    public WeaponFireEvent(Weapon weapon) {

        this.weapon = weapon;
    }

    public Weapon getWeapon(){
        return weapon;
    }
}
