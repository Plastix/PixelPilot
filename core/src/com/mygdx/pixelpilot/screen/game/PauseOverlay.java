package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.screen.ui.ShadowImageButton;
import com.mygdx.pixelpilot.screen.ui.ShadowLabel;
import com.mygdx.pixelpilot.screen.ui.ShadowTextButton;

public class PauseOverlay extends Stage {

    private Table table;
    private FreeTypeFontGenerator generator;
    private ShadowTextButton.ShadowTextButtonStyle buttonStyle;

    public PauseOverlay() {
        super(new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera()));
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.font.pixel));

        table = new Table();
        table.setFillParent(true);
        table.top().center();

        Texture pixel = new Texture(Assets.image.pixel);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(pixel)).tint(new Color(0,0,0,0.5f)));

        createButtonStyle();

        addPauseLabel();
        table.row().expandX();
        addResumeButton();
        table.row().expandX();
        addRestartButton();
        table.row().expandX();
        addMenuButton();
        table.row();
        addMusicToggleButton();
        addSoundToggleButton();


        this.addActor(table);

    }

    private void addPauseLabel(){
        ShadowLabel.ShadowLabelStyle style = new ShadowLabel.ShadowLabelStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        style.font =  generator.generateFont(parameter);
        style.fontColor = new Color(1,1,1,1);
        style.shadowColor = new Color(0,0,0,1);
        style.shadowDepth = 7;

        ShadowLabel pause = new ShadowLabel("Paused", style);
        table.add(pause).colspan(2).pad(0,0,50,0);
    }

    private void createButtonStyle(){
        buttonStyle = new ShadowTextButton.ShadowTextButtonStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        buttonStyle.font = generator.generateFont(parameter);
        buttonStyle.fontColor = new Color(0.9f, 0.92f, 0.36f,1);
        buttonStyle.shadowColor = new Color(0.6f,0.6f,0.2f,1);
        buttonStyle.shadowDepth = 5;
    }

    private void addResumeButton(){
        ShadowTextButton resume = new ShadowTextButton("Resume", buttonStyle);
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Events.emit(new GameResumeEvent(), this);
            }
        });

        table.add(resume).colspan(2);
    }

    private void addRestartButton(){
        ShadowTextButton restart = new ShadowTextButton("Restart", buttonStyle);
        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Restart!");
            }
        });

        table.add(restart).colspan(2);
    }

    private void addMenuButton(){
        ShadowTextButton menu = new ShadowTextButton("Menu", buttonStyle);
        menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Menu!");
            }
        });

        table.add(menu).colspan(2);
    }

    private void addMusicToggleButton(){
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Assets.image.music_icon)));
        style.imageChecked = new TextureRegionDrawable(new TextureRegion(new Texture(Assets.image.music_icon_checked)));
        style.shadowColor = new Color(0,0,0,1);
        style.shadowDepth = 5;
        final ShadowImageButton music = new ShadowImageButton(style);

        music.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Toggled music!");
            }
        });

        table.add(music).size(50,50).right().pad(40, 0, 10, 20);
    }

    private void addSoundToggleButton(){
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Assets.image.sound_icon)));
        style.imageChecked = new TextureRegionDrawable(new TextureRegion(new Texture(Assets.image.sound_icon_checked)));
        style.shadowColor = new Color(0,0,0,1);
        style.shadowDepth = 5;
        ShadowImageButton sound = new ShadowImageButton(style);

        sound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Toggle sound!");
            }
        });

        table.add(sound).size(50,50).left().pad(40, 20, 10, 0);
    }

    @Override
    public void dispose() {
        super.dispose();
        generator.dispose();
    }
}
