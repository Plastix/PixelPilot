package com.mygdx.pixelpilot.game.manager;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Manager;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.pixelpilot.game.component.ShadowComponent;
import com.mygdx.pixelpilot.game.component.Sprite2D;

@Wire
public class ShadowManager extends Manager {

    public ComponentMapper<ShadowComponent> shadow;
    public ComponentMapper<Sprite2D> sprite;

    @Override
    public void added(Entity e) {
        if (shadow.has(e) && sprite.has(e)) {
            ShadowComponent shadow = this.shadow.get(e);
            Sprite2D sprite2D = sprite.get(e);
            Sprite s = new Sprite(sprite2D.sprite);
            s.setColor(shadow.shadowColor);
            s.setScale(shadow.scaleX, shadow.scaleY);
            shadow.setSprite(s);
        }
    }
}
