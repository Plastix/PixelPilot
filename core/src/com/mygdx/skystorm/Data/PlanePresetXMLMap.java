package com.mygdx.skystorm.data;

import com.mygdx.skystorm.plane.PlanePreset;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("plane-presets")
public class PlanePresetXMLMap {

    @Element(name = "plane-preset", targetType = PlanePreset.class)
    public List<PlanePreset> planePresets;
}
