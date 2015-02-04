package com.mygdx.skystorm.world.background;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.skystorm.world.background.theme.BackdropTheme;


public class Backdrop extends Group {

    private int tilesX;
    private int tilesY;
    private BackdropTheme theme;

    public Backdrop(int tilesX, int tilesY, int scale, BackdropTheme theme) {
        this.tilesX = tilesX;
        this.tilesY = tilesY;
        this.theme = theme;

        Image backdrop = new Image(new Texture(renderTexture(), false));
        backdrop.setScale(scale);
        setWidth(backdrop.getWidth());
        setHeight(backdrop.getHeight());
        this.addActor(backdrop);
    }

    private Pixmap renderTexture(){
        float[][] noise = theme.getNoise(tilesX, tilesY);
        Pixmap pixMap = new Pixmap(tilesX, tilesY, Pixmap.Format.RGB888);

        for(int i = 0; i < tilesX; i++){
            for(int j = 0; j < tilesY; j++){
                float height = noise[i][j];
                theme.colorize(pixMap, height);
                pixMap.drawPixel(i, j);
            }
        }
        return pixMap;
    }
}
