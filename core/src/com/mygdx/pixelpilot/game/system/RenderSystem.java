package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.game.component.*;
import com.mygdx.pixelpilot.game.manager.BackdropManager;

@Wire
public class RenderSystem extends EntityProcessingSystem {

    private SpriteBatch batch;
    private ExtendViewport viewport;
    private ComponentMapper<Position> position;
    private ComponentMapper<Rotation> rotation;
    private ComponentMapper<Sprite2D> sprite2d;
    private ComponentMapper<Renderable> renderable;
    private ComponentMapper<Size> size;
    private Backdrop backdrop;

    @SuppressWarnings("unchecked")
    public RenderSystem() {
        super(
                Aspect.getAspectForAll(
                        Renderable.class,
                        Position.class,
                        Rotation.class,
                        Size.class,
                        Sprite2D.class
                )
        );

        batch = new SpriteBatch();

        viewport = new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera());
        // Pass in true to center camera
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        backdrop = Assets.manager.get("backdrop", Backdrop.class);
        backdrop.setSize(3000, 3000);
    }

    @Override
    protected void begin() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        backdrop.draw(batch, 1);
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void process(Entity e) {
        if (renderable.get(e).isVisible) {
            Sprite2D sprite2d = this.sprite2d.get(e);
            Position position = this.position.get(e);
            Rotation rotation = this.rotation.get(e);
            Size size = this.size.get(e);
            sprite2d.sprite.setPosition(position.x, position.y);
            sprite2d.sprite.setRotation(rotation.rotation);
            sprite2d.sprite.setSize(size.width, size.height);
            sprite2d.sprite.draw(batch);
        }
    }
}
