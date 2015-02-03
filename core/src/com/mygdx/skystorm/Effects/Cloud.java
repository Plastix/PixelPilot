package com.mygdx.skystorm.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.skystorm.util.Utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Cloud extends Group {

    private static final FileHandle cloudImg = Gdx.files.internal("cloud.png");

    public Cloud(float x, float y, float sizeX, float sizeY, int numPuffs) {
        for (int i = 0; i < numPuffs; i++) {
            Image puff = new Image(new Texture(cloudImg));
            puff.setOrigin(puff.getWidth() / 2, puff.getHeight() / 2);
            puff.setPosition(x - puff.getWidth() / 2 + Utils.randomFloat(-sizeX, sizeX),
                             y - puff.getHeight() / 2 + Utils.randomFloat(-sizeY, sizeY));
            puff.setScale(-Utils.randomFloat(0.1f, 0.3f));
            puff.setColor(1f, 1f, 1f, (float) (Math.random()) / 2f);
            this.addActor(puff);
        }
    }
}
