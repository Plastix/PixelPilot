package com.mygdx.skystorm.data;

import com.badlogic.gdx.Gdx;
import org.xmappr.Xmappr;

import java.io.File;
import java.io.FileReader;

public class XMLParser {


    private static final String WEAPONS_XML_PATH = "config/Weapons.xml";
    private static final String PLANE_XML_PATH = "config/PlaneDefinitions.xml";
    private static final String PRESET_XML_PATH = "config/PlanePresets.xml";

    public static void parsePlanePresets(){
        if(GameData.planeDefinitions == null && GameData.weaponDefinitions == null){
            parsePlaneWeapons();
            parsePlaneDefinitions();
        }
        PlanePresetXMLMap data = (PlanePresetXMLMap) mapXMLtoClass(Gdx.files.internal(PRESET_XML_PATH).file(), PlanePresetXMLMap.class);
        GameData.planePresets = data.planePresets;
    }

    public static void parsePlaneWeapons(){
        WeaponXMLMap data = (WeaponXMLMap) mapXMLtoClass(Gdx.files.internal(WEAPONS_XML_PATH).file(), WeaponXMLMap.class);
        GameData.weaponDefinitions = data.weaponDefinitions;
    }

    public static void parsePlaneDefinitions(){
        PlaneDefinitionXMLMap data = (PlaneDefinitionXMLMap) mapXMLtoClass(Gdx.files.internal(PLANE_XML_PATH).file(), PlaneDefinitionXMLMap.class);
        GameData.planeDefinitions = data.planeDefinitions;
    }

    private static Object mapXMLtoClass(File xmlFile, Class map){
        try {
            Xmappr xm = new Xmappr(map);
            FileReader reader = new FileReader(xmlFile);
            return xm.fromXML(reader);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
