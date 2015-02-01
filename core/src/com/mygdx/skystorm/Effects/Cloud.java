package com.mygdx.skystorm.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.skystorm.Utils.Utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Cloud extends Group {

    private static final FileHandle cloudImg = Gdx.files.internal("cloud.png");

    public Cloud(float x, float y, int numPuffs) {
        for (int i = 0; i < 15; i++) {
            Image puff = new Image(new Texture(cloudImg));
            puff.setOrigin(puff.getWidth() / 2, puff.getHeight() / 2);
            puff.setPosition(x - puff.getWidth() / 2 + Utils.randomFloat(-250, 250), y - puff.getHeight() / 2 + Utils.randomFloat(-250, 250));
            puff.setScale(-(float) (Math.random()));
            puff.setColor(1f, 1f, 1f, (float) (Math.random()) / 2f);
            MoveToAction slideAcrossScreen = new MoveToAction();
            slideAcrossScreen.setPosition(1000, y);
            slideAcrossScreen.setDuration(Utils.randomInt(50, 99));
            puff.addAction(forever(sequence(slideAcrossScreen, moveTo(-1000, y))));
            this.addActor(puff);
        }
    }
}
