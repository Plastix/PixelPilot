package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pixelpilot.game.component.Position;
import com.mygdx.pixelpilot.game.component.Renderable;
import com.mygdx.pixelpilot.game.component.Rotation;
import com.mygdx.pixelpilot.game.component.Sprite2D;

@Wire
public class RenderSystem extends EntityProcessingSystem {

    private SpriteBatch batch;
    private ComponentMapper<Position> position;
    private ComponentMapper<Rotation> rotation;
    private ComponentMapper<Sprite2D> sprite2d;
    private ComponentMapper<Renderable> renderable;

    public RenderSystem() {
        super(
                Aspect.getAspectForAll(
                        Renderable.class,
                        Position.class,
                        Rotation.class,
                        Sprite2D.class
                )
        );
        batch = new SpriteBatch();
    }

    @Override
    protected void begin() {
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void process(Entity e) {
        if (renderable.get(e).isVisible) {
            Sprite2D sprite2d = sprite2d.get(e);
            sprite2d.sprite.draw(batch);

        }
    }
}
