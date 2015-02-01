package com.mygdx.skystorm.Data;

import com.mygdx.skystorm.Plane.PlaneProperty;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("plane-properties")
public class PlanePropertyXMLMap {

    @Element(name = "plane-property", targetType = PlaneProperty.class)
    public List<PlaneProperty> planeProperties;
}
