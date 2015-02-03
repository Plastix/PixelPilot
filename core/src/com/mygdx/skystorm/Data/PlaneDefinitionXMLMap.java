package com.mygdx.skystorm.data;

import com.mygdx.skystorm.plane.PlaneDefinition;
import org.xmappr.Element;
import org.xmappr.RootElement;

import java.util.List;

@RootElement("plane-definitions")
public class PlaneDefinitionXMLMap {

    @Element(name = "plane-definition", targetType = PlaneDefinition.class)
    public List<PlaneDefinition> planeDefinitions;
}
