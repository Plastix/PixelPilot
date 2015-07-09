package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Renderable extends Component {

    public boolean isVisible;

    public Renderable() {
        isVisible = true;
    }

    @Override
    public String toString() {
        return "Renderable{" +
                "isVisible=" + isVisible +
                '}';
    }
}
