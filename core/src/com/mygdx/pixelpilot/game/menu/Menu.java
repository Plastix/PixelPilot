package com.mygdx.pixelpilot.game.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;

public class Menu extends Stage {

    protected Table table;

    public Menu() {
        super(new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera()));

        table = new Table();
        table.setFillParent(true);

        this.addActor(table);
    }

    protected void dimBackground(){
        Texture pixel = new Texture(Assets.Images.pixel);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(pixel)).tint(new Color(0,0,0,0.65f)));
    }
}
