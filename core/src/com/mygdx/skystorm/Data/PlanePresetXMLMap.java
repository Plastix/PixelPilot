package com.mygdx.skystorm.Data;

import com.mygdx.skystorm.Plane.PlanePreset;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("plane-presets")
public class PlanePresetXMLMap {

    @Element(name = "plane-preset", targetType = PlanePreset.class)
    public List<PlanePreset> planePresets;
}
