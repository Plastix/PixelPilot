package com.mygdx.pixelpilot.plane;

import com.mygdx.pixelpilot.plane.controller.Controller;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.WeaponSlot;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.InstalledWeaponDefinition;

import java.util.List;

public class PlanePreset {

    public String name;
    public PlaneDefinition planeDefinition;
    public List<InstalledWeaponDefinition> weaponDefinitions;
    public Class<? extends Controller> controller;

    public void resolveWeaponSlotLinkages() {
        for(InstalledWeaponDefinition installation : weaponDefinitions) {
            boolean found = false;
            for (WeaponSlot slot : planeDefinition.weaponSlots) {
                if(installation.slot.name.equals(slot.name)) {
                    installation.mount(slot);
                    found = true;
                }
            }
            if(!found) {
                throw new RuntimeException("Slot name mismatch [" + installation.slot.name
                        + "] -- Check in PlanePresets for misspelled slot name");
            }
        }
    }

    @Override
    public String toString(){
        return String.format("PlanePreset \"%s\", using plane: (\n\t%s\n) and def: (\n\t%s\n) with controller: (%s)",
                name, planeDefinition, weaponDefinitions, controller.getSimpleName());
    }
}
