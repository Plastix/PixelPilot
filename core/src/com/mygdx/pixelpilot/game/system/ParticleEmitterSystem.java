package com.mygdx.pixelpilot.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.game.camera.GameCamera;
import com.mygdx.pixelpilot.game.component.ParticleEmitter;
import com.mygdx.pixelpilot.game.component.Position;

//TODO Figure out how to cull particle emitters
@Wire
public class ParticleEmitterSystem extends EntityProcessingSystem {

    private ComponentMapper<Position> position;
    private ComponentMapper<ParticleEmitter> particle;
    private SpriteBatch batch;

    @Wire
    private ExtendViewport viewport;

    @SuppressWarnings("unchecked")
    public ParticleEmitterSystem() {
        super(
                Aspect.getAspectForAll(
                        Position.class,
                        ParticleEmitter.class
                ));
        batch = new SpriteBatch();
    }

    @Override
    protected void begin() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void process(Entity entity) {
        Position pos = position.get(entity);
        ParticleEmitter emitter = particle.get(entity);

        ParticleEffect particleEffect = emitter.particleEffect;
        particleEffect.setPosition(pos.x, pos.y);

        particleEffect.draw(batch, Gdx.graphics.getDeltaTime());
    }
}
