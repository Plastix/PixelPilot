package com.mygdx.pixelpilot.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.pixelpilot.data.Assets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Cloud extends Group {

    public Cloud(float x, float y, float sizeX, float sizeY, int numPuffs) {
        for (int i = 0; i < numPuffs; i++) {
            Image puff = new Image(new Texture(Assets.image.cloud));
            puff.setOrigin(puff.getWidth() / 2, puff.getHeight() / 2);
            puff.setPosition(x - puff.getWidth() / 2 + MathUtils.random(-sizeX, sizeX),
                             y - puff.getHeight() / 2 + MathUtils.random(-sizeY, sizeY));
            puff.setScale(MathUtils.random(0.1f * 300, 0.3f * 300));
            puff.setColor(1f, 1f, 1f, (float) (Math.random()) / 2f);
            this.addActor(puff);
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