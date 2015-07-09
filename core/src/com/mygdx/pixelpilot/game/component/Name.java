package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;

public class Name extends Component {

    public String name;

    public Name(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
