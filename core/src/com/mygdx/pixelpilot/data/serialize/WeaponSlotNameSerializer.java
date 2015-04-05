package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.plane.shooty.weapon.utils.WeaponSlot;

public class WeaponSlotNameSerializer implements ScalarSerializer<WeaponSlot> {
    @Override
    public String write(WeaponSlot object) throws YamlException {
        return null;
    }

    @Override
    public WeaponSlot read(String value) throws YamlException {
        WeaponSlot s = new WeaponSlot();
        s.name = value;
        return s;
    }
}
