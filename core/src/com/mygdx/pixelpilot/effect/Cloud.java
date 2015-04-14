package com.mygdx.pixelpilot.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.pixelpilot.data.Assets;

import java.util.ArrayList;
import java.util.List;

public class Cloud extends Actor {

    /**
     * Store a list of children Sprites
     * Because Sprites are not Scene2D actors we
     * can't use Group
     */
    private List<Sprite> puffs;

    public Cloud(float x, float y, float sizeX, float sizeY, int numPuffs) {

        this.puffs = new ArrayList<Sprite>();
        Rectangle bounds = new Rectangle(x, y, sizeX, sizeY);

        for (int i = 0; i < numPuffs; i++) {
            Sprite puff = new Sprite(new Texture(Assets.image.pixel));
            puff.setOrigin(puff.getWidth() / 2, puff.getHeight() / 2);
            puff.setPosition(x - puff.getWidth() / 2 + MathUtils.random(-sizeX, sizeX),
                    y - puff.getHeight() / 2 + MathUtils.random(-sizeY, sizeY));
            puff.setScale(MathUtils.random(0.1f * 300, 0.3f * 300));
            puff.setColor(1f, 1f, 1f, (float) (Math.random()) / 2f);
            this.puffs.add(puff);
            bounds.merge(puff.getBoundingRectangle());
        }
        //Set bounds of our "group" to the union of all of the children bounds
        //This makes our clouds cull nicely in World
        this.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(Sprite puff : puffs){
            puff.draw(batch, parentAlpha);
        }
    }

    public static Group generateClouds(int count) {
        Group clouds = new Group();
        for(int i = 0; i < count; i++){
            Cloud cloud = new Cloud(
                    MathUtils.random(0, Gdx.graphics.getWidth()),
                    MathUtils.random(0, Gdx.graphics.getHeight()),
                    75, 75, 5);
            clouds.addActor(cloud);
        }

        return clouds;
    }
}