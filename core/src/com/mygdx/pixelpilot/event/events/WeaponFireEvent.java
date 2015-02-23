package com.mygdx.pixelpilot.event.events;

import com.mygdx.pixelpilot.plane.Weapon;

public class WeaponFireEvent extends GameEvent {
    private Weapon weapon;

    public WeaponFireEvent(Weapon weapon) {

        this.weapon = weapon;
    }

    public Weapon getWeapon(){
        return weapon;
    }
}
