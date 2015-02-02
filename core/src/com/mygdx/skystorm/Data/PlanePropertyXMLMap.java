package com.mygdx.skystorm.data;

import com.mygdx.skystorm.plane.PlaneProperty;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("plane-properties")
public class PlanePropertyXMLMap {

    @Element(name = "plane-property", targetType = PlaneProperty.class)
    public List<PlaneProperty> planeProperties;
}
