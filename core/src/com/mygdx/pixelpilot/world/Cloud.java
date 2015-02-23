package com.mygdx.pixelpilot.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.util.Utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Cloud extends Group {

    public Cloud(float x, float y, float sizeX, float sizeY, int numPuffs) {
        for (int i = 0; i < numPuffs; i++) {
            Image puff = new Image(new Texture(Assets.image.cloud));
            puff.setOrigin(puff.getWidth() / 2, puff.getHeight() / 2);
            puff.setPosition(x - puff.getWidth() / 2 + Utils.randomFloat(-sizeX, sizeX),
                             y - puff.getHeight() / 2 + Utils.randomFloat(-sizeY, sizeY));
            puff.setScale(Utils.randomFloat(0.1f*300, 0.3f*300));
            puff.setColor(1f, 1f, 1f, (float) (Math.random()) / 2f);
            this.addActor(puff);
        }
    }

    public static Group generateClouds(int count) {
        Group clouds = new Group();
        for(int i = 0; i < count; i++){
            Cloud cloud = new Cloud(
                    Utils.randomFloat(0, Gdx.graphics.getWidth()),
                    Utils.randomFloat(0, Gdx.graphics.getHeight()),
                    75, 75, 5);
            clouds.addActor(cloud);
        }

        return clouds;
    }
}