package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Gun extends Component {

    public int shotCount;
    public float reloadTime;

    public Gun(int shotCount, float reloadTime) {
        this.shotCount = shotCount;
        this.reloadTime = reloadTime;
    }
}
