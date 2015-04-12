package com.mygdx.pixelpilot.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.event.events.screen.ScreenChangeEvent;
import com.mygdx.pixelpilot.screen.game.CampaignGameScreen;
import com.mygdx.pixelpilot.screen.ui.ShadowImageButton;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenu extends Menu {

    TextButton playButton;
    ShadowImageButton planesButton;
    ShadowImageButton settings;
    Image logo;
    Label text1, text2;

    public MainMenu() {
        createMenuGraphics();
    }

    private void createMenuGraphics() {
        // create title logo header
        Table logoTable = new Table();

        Label.LabelStyle logoStyle = new Label.LabelStyle();
        logoStyle.font = Assets.manager.get("logo-font");

        logo = new Image(new Texture(Assets.image.menu_logo));
        logo.setScaling(Scaling.fit);
        text1 = new Label("Pixel", logoStyle);
        text2 = new Label("Pilot", logoStyle);

        logoTable.add(text1).padRight(15).padLeft(10).fill().expand();
        logoTable.add(logo).fill().expand().prefSize(250, 250);
        logoTable.add(text2).padLeft(15).padRight(10).fill().expand();
        table.top();
        table.add(logoTable)
                .expandX()
                .padTop(20).height(200);

        table.row();

        Table buttonTable = new Table();
        buttonTable.top().center();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = Assets.manager.get("play-font");

        playButton = new TextButton("Play", style);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Play clicked!");
                Events.getBus().publish(new ScreenChangeEvent(new CampaignGameScreen()));
//                slideButtonsOutAndTransitionTo(new CampaignGameScreen());
            }
        });
        buttonTable.add(playButton).colspan(2);
        animatePlayButton();

        buttonTable.row().expand().fill();

        ShadowImageButton.ShadowImageButtonStyle settingStyle = new ShadowImageButton.ShadowImageButtonStyle();
        Texture wrench = new Texture(Assets.image.settings);
        settingStyle.imageUp = new TextureRegionDrawable(new TextureRegion(wrench));
        settingStyle.shadowDepth = 7;
        settingStyle.shadowColor = new Color(0, 0, 0, 1);
        settings = new ShadowImageButton(settingStyle);
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Options clicked!");
                Events.getBus().publish(new MenuOpenEvent(new OptionsMenu()));
            }
        });
        buttonTable.add(settings).size(100, 100).left().pad(10).bottom();

        ShadowImageButton.ShadowImageButtonStyle planesStyle = new ShadowImageButton.ShadowImageButtonStyle();
        Texture planeIcon = new Texture(Assets.image.plane);
        planesStyle.imageUp = new TextureRegionDrawable(new TextureRegion(planeIcon));
        planesStyle.shadowDepth = 7;
        planesStyle.shadowColor = new Color(0, 0, 0, 1);
        planesButton = new ShadowImageButton(planesStyle);
        planesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("My Planes clicked!");
            }
        });
        buttonTable.add(planesButton).size(100, 100).right().pad(10).bottom();
        table.add(buttonTable).expand().fill();

    }

    public void animatePlayButton() {
        RepeatAction pulse = forever(
                sequence(
                        scaleBy(0.2f, 0.2f, 0.35f, Interpolation.pow2),
                        scaleBy(-0.2f, -0.2f, 0.35f, Interpolation.pow2)
                ));
        pulse.setActor(playButton);
        this.addAction(pulse);
    }

    public void slideButtonsOutAndTransitionTo(final Screen next) {
        MoveToAction movePlay = new MoveToAction();
        movePlay.setDuration(0.7f);
        movePlay.setInterpolation(Interpolation.exp10);
        movePlay.setPosition(playButton.getX(), -playButton.getHeight());
        movePlay.setActor(playButton);

        MoveToAction movePlanes = new MoveToAction();
        movePlanes.setDuration(0.5f);
        movePlanes.setInterpolation(Interpolation.exp10);
        movePlanes.setPosition(Gdx.graphics.getWidth() + planesButton.getWidth() * 2, planesButton.getY());
        movePlanes.setActor(planesButton);

        MoveToAction moveSettings = new MoveToAction();
        moveSettings.setDuration(0.3f);
        moveSettings.setInterpolation(Interpolation.exp10);
        moveSettings.setPosition(-settings.getWidth() * 2, settings.getY());
        moveSettings.setActor(settings);

        MoveToAction moveText1 = new MoveToAction();
        moveText1.setDuration(0.4f);
        moveText1.setInterpolation(Interpolation.pow2);
        moveText1.setPosition(-text1.getWidth() * 2, text1.getY());
        moveText1.setActor(text1);

        MoveToAction moveText2 = new MoveToAction();
        moveText2.setDuration(0.4f);
        moveText2.setInterpolation(Interpolation.pow2);
        moveText2.setPosition(Gdx.graphics.getWidth() + text2.getWidth(), text2.getY());
        moveText2.setActor(text2);

        logo.setOrigin(Align.center);
        ParallelAction moveLogo = Actions.parallel(
                Actions.scaleBy(-0.5f, -0.5f, 0.4f, Interpolation.pow2),
                Actions.fadeOut(0.4f, Interpolation.pow2));
        moveLogo.setActor(logo);

        AlphaAction fadeOut = Actions.fadeOut(0.2f, Interpolation.pow2Out);
        fadeOut.setActor(this.getRoot());

        this.addAction(
                Actions.sequence(
                        Actions.parallel(movePlay, movePlanes, moveSettings, moveText1, moveText2, moveLogo),
                        fadeOut,
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                Events.getBus().publish(new ScreenChangeEvent(next));
                            }
                        })
                ));
    }
}
