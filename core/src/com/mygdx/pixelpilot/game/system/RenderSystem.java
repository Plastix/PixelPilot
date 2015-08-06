package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.effect.background.Backdrop;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.ResizeEvent;
import com.mygdx.pixelpilot.game.StageConfig;
import com.mygdx.pixelpilot.game.camera.ParallaxUtil;
import com.mygdx.pixelpilot.game.component.*;
import net.engio.mbassy.listener.Handler;

@Wire
public class RenderSystem extends EntityProcessingSystem {

    private SpriteBatch batch;

    @Wire
    private ExtendViewport viewport;
    @Wire // do I need two @Wires?
    private StageConfig config;
    private ComponentMapper<Position> position;
    private ComponentMapper<Rotation> rotation;
    private ComponentMapper<Sprite2D> sprite2d;
    private ComponentMapper<Renderable> renderable;
    private ComponentMapper<Size> size;
    private ComponentMapper<Shadow> shadow;
    private ComponentMapper<Parallax> parallax;
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
        backdrop.setSize(config.worldBounds.width, config.worldBounds.height);
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

            //Update batch with parallax
            if (parallax.has(e)) {
                Parallax p = parallax.get(e);
                batch.setProjectionMatrix(ParallaxUtil.calculateParallaxMatrix(viewport.getCamera(), p.parallaxX, p.parallaxY));
            }
//            } else {
//                batch.setProjectionMatrix(viewport.getCamera().combined);
//            }


            Sprite2D sprite2d = this.sprite2d.get(e);
            Position position = this.position.get(e);
            Rotation rotation = this.rotation.get(e);

            //Draw a shadow if the entity has one
            if (shadow.has(e)) {
                Shadow s = shadow.get(e);
                s.sprite.setRotation(rotation.rotation);
                s.sprite.setPosition(position.x - s.sprite.getWidth() / 2 - s.offsetX, position.y - s.sprite.getHeight() / 2 - s.offsetY);
                s.sprite.draw(batch);
            }

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
