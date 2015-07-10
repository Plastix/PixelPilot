package com.mygdx.pixelpilot.game.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class ParticleEmitter extends Component {

    public ParticleEffect particleEffect;

    public ParticleEmitter(ParticleEffect effect) {
        particleEffect = new ParticleEffect(effect);
    }


}
