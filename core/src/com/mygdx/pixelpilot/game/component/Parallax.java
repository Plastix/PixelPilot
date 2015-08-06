package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Parallax extends Component {

    public float parallaxX;
    public float parallaxY;

    public Parallax(float parallaxX, float parallaxY) {
        this.parallaxX = parallaxX;
        this.parallaxY = parallaxY;
    }
}
