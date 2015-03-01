package com.mygdx.pixelpilot.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.event.events.screen.MenuCloseEvent;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.screen.ui.ShadowImageButton;

public class PauseMenu extends Menu {

    private FreeTypeFontGenerator generator;
    private final PauseMenu instance;

    public PauseMenu() {
        this.instance = this;
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.font.pixel));

        this.dimBackground();
        table.top().center();

        addPauseLabel();
        table.row().expandX();
        addOptionsButton();
        addResumeButton();
        addRestartButton();
        addMenuButton();
    }

    private void addPauseLabel(){
        Label.LabelStyle style = new Label.LabelStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        parameter.color = new Color(1,1,1,1);
        parameter.shadowColor = new Color(0,0,0,1);
        parameter.shadowOffsetY = 7;
        style.font =  generator.generateFont(parameter);


        Label pause = new Label("Paused", style);
        table.add(pause).colspan(4).pad(0,0,40,0);
    }


    private void addResumeButton(){
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture wrench = new Texture(Assets.image.play_icon);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(wrench));
        style.shadowDepth = 8;
        style.shadowColor = new Color(0.15f,0.5f,0.1f,1);
        ShadowImageButton resume = new ShadowImageButton(style);
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Events.emit(new MenuCloseEvent(), this);
                Events.emit(new GameResumeEvent(), this);
            }
        });
        table.add(resume).size(150, 150).right().padRight(25);
    }

    private void addRestartButton(){
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture wrench = new Texture(Assets.image.restart_icon);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(wrench));
        style.shadowDepth = 8;
        style.shadowColor = new Color(0.4f,0.09f,0.09f,1);
        ShadowImageButton restart = new ShadowImageButton(style);
        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Restart!");
            }
        });
        table.add(restart).size(150, 150).left().padLeft(25);
    }

    private void addOptionsButton(){
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture wrench = new Texture(Assets.image.settings);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(wrench)).tint(new Color(0.9f, 0.92f, 0.36f,1));
        style.shadowDepth = 7;
        style.shadowColor = new Color(0.6f,0.6f,0.2f,1);
        ShadowImageButton options = new ShadowImageButton(style);
        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Options!");
                Events.emit(new MenuOpenEvent(new OptionsMenu()), this);
            }
        });
        table.add(options).size(100, 100).right();
    }

    private void addMenuButton(){
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture wrench = new Texture(Assets.image.menu_icon);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(wrench)).tint(new Color(0.9f, 0.92f, 0.36f,1));
        style.shadowDepth = 7;
        style.shadowColor = new Color(0.6f,0.6f,0.2f,1);
        ShadowImageButton menu = new ShadowImageButton(style);
        menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Menu!");
                Events.emit(new ScreenChangeEvent(new MenuScreen()), this);
                Events.emit(new MenuOpenEvent(new MainMenu()), this);
            }
        });
        table.add(menu).size(100, 100).left();
    }


    @Override
    public void dispose() {
        generator.dispose();
    }
}
