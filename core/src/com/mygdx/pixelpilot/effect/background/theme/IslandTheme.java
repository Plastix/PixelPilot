package com.mygdx.pixelpilot.effect.background.theme;

import com.badlogic.gdx.graphics.*;
import com.mygdx.pixelpilot.effect.background.SimplexNoise;


public class IslandTheme implements BackdropTheme {

    @Override
    public void colorize(Pixmap pixmap, float height) {
        if(height >= 0.5){ //Darker green
            pixmap.setColor(new Color(0.19f, 0.71f, 0.3f, 1));
        }
        else if(height >= 0.2 && height < 0.5){ //Green
            pixmap.setColor(new Color(0.24f, 0.8f, 0.36f, 1));
        }
        else if(height >= 0.1 && height < 0.2){ //Beach
            pixmap.setColor(new Color(0.96f, 0.91f, 0.68f, 1));
        }
        else if(height < 0.1 && height >= -0.2){ //Shallow water
            pixmap.setColor(new Color(0.36f, 0.81f, 0.93f, 1));
        }else { //Deep water
            pixmap.setColor(new Color(0.3f, 0.66f, 0.9f, 1));
        }
    }

    @Override
    public float[][] getNoise(int width, int height) {
        return SimplexNoise.generateOctavedSimplexNoise(width, height, 3, 0.4f, 0.009f);
    }
}
