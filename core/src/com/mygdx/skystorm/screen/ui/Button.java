package com.mygdx.skystorm.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.mygdx.skystorm.data.Resources;

// TODO: Ensure BitmapFont.dispose() is called when it is no longer needed (to prevent mem leaks)
public class Button extends Image{
    // consider making this a cached resource (static)
    private BitmapFont btnFont;
    private String btnText;

    public Button(final String text, final Texture backgroundUp, final Texture backgroundDown, final Runnable callback){
        super(backgroundUp);
        final Button self = this;
        btnText = text;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Resources.menu_font));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (136 * Gdx.graphics.getDensity()); // how to scale this?
        btnFont = generator.generateFont(parameter);
        generator.dispose();

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

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        BitmapFont.TextBounds bounds =  btnFont.getBounds(btnText);

        btnFont.setColor(175f / 255f, 175f / 255f, 175f / 255f, 1);
        btnFont.draw(batch, btnText, this.getX(Align.center) - bounds.width / 2,
                this.getY(Align.center) - 6 + bounds.height/2);

        btnFont.setColor(1,1,1,1);
        btnFont.draw(batch, btnText, this.getX(Align.center) - bounds.width/2,
                this.getY(Align.center)+bounds.height/2);
    }

    @Override
    public void setBounds (float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        float scaleX = width / 960;
        float scaleY = height / 540;
        float calculatedScale = 9*scaleX*scaleY;
        if(btnFont != null && calculatedScale != 0 )
            btnFont.setScale( calculatedScale );
    }




}
