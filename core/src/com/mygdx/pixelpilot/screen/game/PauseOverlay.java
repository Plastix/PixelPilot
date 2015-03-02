package com.mygdx.pixelpilot.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.screen.ui.ShadowTextButton;

public class PauseOverlay extends Stage {

    private Table table;
    private PauseOverlay pause;

    public PauseOverlay() {
        this.setViewport(new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera()));
        Gdx.input.setInputProcessor(this);

        table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(true);
        pause = this;

        addResumeButton();

        this.addActor(table);

    }

    private void addResumeButton(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.font.pixel));
        ShadowTextButton.ShadowTextButtonStyle style = new ShadowTextButton.ShadowTextButtonStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 250;
        style.font =  generator.generateFont(parameter);
        generator.dispose();
        style.fontColor = new Color(0.9f, 0.92f, 0.36f,1);
        style.shadowColor = new Color(0.72f,0.74f,0.3f,1);
        style.shadowDepth = 8;
        ShadowTextButton resume = new ShadowTextButton("Resume", style);
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Events.emit(new GameResumeEvent(pause), this);
            }
        });

        table.add(resume).expand().fill().center();
    }
}
