package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.ResizeEvent;
import com.mygdx.pixelpilot.game.camera.GameCamera;
import com.mygdx.pixelpilot.game.component.*;
import net.engio.mbassy.listener.Handler;

@Wire
public class RenderSystem extends EntityProcessingSystem {

    private SpriteBatch batch;
    @Wire
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
        Events.getBus().subscribe(this);

    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();

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
        viewport.getCamera().update();
        if (renderable.get(e).isVisible) {
            Sprite2D sprite2d = this.sprite2d.get(e);
            Position position = this.position.get(e);
            Rotation rotation = this.rotation.get(e);
            Size size = this.size.get(e);
            sprite2d.sprite.setPosition(position.x - sprite2d.sprite.getWidth() / 2, position.y - sprite2d.sprite.getHeight() / 2);
            sprite2d.sprite.setRotation(rotation.rotation);
            sprite2d.sprite.setScale(size.scaleX, size.scaleY);
            sprite2d.sprite.draw(batch);
        }
    }

    @Handler
    public void onResize(ResizeEvent event) {
        viewport.update(event.width, event.height);
    }
}
