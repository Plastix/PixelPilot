package com.mygdx.skystorm.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// TODO: Add support for drawing text on top of button
public class Button extends Image{
    public Button(final String text, final Texture backgroundUp, final Texture backgroundDown, final Runnable callback){
        super(backgroundUp);
        final Button self = this;
        this.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                self.setDrawable(new TextureRegionDrawable(new TextureRegion(backgroundDown)));
                return true;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(this.isOver())
                    callback.run();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                super.touchUp(event, x, y, pointer, button);
                self.setDrawable(new TextureRegionDrawable(new TextureRegion(backgroundUp)));

            }

            @Override
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                if(!this.isOver()) {
                    self.setDrawable(new TextureRegionDrawable(new TextureRegion(backgroundUp)));
                }else{
                    self.setDrawable(new TextureRegionDrawable(new TextureRegion(backgroundDown)));
                }

            }
        });

    }

    @Override
    public Actor hit (float x, float y, boolean touchable) {
        return x >= getImageX()
                && x < getImageWidth()+getImageX()
                && y >= getImageY()
                && y < getImageHeight()+getImageY() ? this : null;
    }
}
