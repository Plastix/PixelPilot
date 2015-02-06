package com.mygdx.skystorm.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.data.Resources;
import com.mygdx.skystorm.screen.ui.Button;
import com.mygdx.skystorm.util.Utils;
import com.mygdx.skystorm.world.Cloud;
import com.mygdx.skystorm.world.background.Backdrop;
import com.mygdx.skystorm.world.background.theme.BackdropFactory;
import com.mygdx.skystorm.world.background.theme.BackdropTheme;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen implements Screen {
    Button playButton;
    Button planesButton;
    Button optionsButton;
    Image logo, text1, text2;
    Backdrop background;
    final SkyStorm game;
    OrthographicCamera camera;
    Stage stage;

    public MainMenuScreen(SkyStorm game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        ScreenViewport view = new ScreenViewport(camera);
        stage = new Stage(view);

        BackdropTheme islands = BackdropFactory.build(BackdropFactory.ThemePreset.ISLANDS);
        background = new Backdrop(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, 4, islands);

        stage.addActor(background);

        for(int i = 0; i < 15; i++) {
            Cloud cloud = new Cloud(
                    Utils.randomFloat(0, Gdx.graphics.getWidth()),
                    Utils.randomFloat(0, Gdx.graphics.getHeight()),
                    75, 75, 5);
            MoveToAction slideAcrossScreen = new MoveToAction();
            slideAcrossScreen.setPosition(1000, cloud.getY());
            slideAcrossScreen.setDuration(Utils.randomInt(50, 99));
            cloud.addAction(forever(sequence(slideAcrossScreen, moveTo(-1000, cloud.getY()))));
            stage.addActor(cloud);
        }

        Gdx.input.setInputProcessor(stage);

        createMenuGraphics();

    }

    private void createMenuGraphics() {

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // create title logo header
        Table logoTable = new Table();

        logo = new Image(new Texture(Resources.menu_logo));
        text1 = new Image(new Texture(Resources.menu_text_pixel));
        text2 = new Image(new Texture(Resources.menu_text_pilot));

        logo.setScaling(Scaling.fit);
        text1.setScaling(Scaling.fit);
        text2.setScaling(Scaling.fit);

        logoTable.add(text1).padRight(15).padLeft(10).fill().expand().minHeight(25).minWidth(50);
        logoTable.add(logo).fill().expand().minSize(70, 70);
        logoTable.add(text2).padLeft(15).padRight(10).fill().expand().minHeight(25).minWidth(50);
        float scale = 0.6f;
        table.top();
        table.add(logoTable)
                .prefSize((logo.getWidth() + text1.getWidth() + text2.getWidth()) * scale, logo.getHeight() * scale)
                .expandX()
                .padTop(20)
                .space(45);

        table.row();

        Table buttonTable = new Table();

        // create the buttons
        final Button playButton = new Button("",
                new Texture(Resources.btn_play_up_img),
                new Texture(Resources.btn_play_down_img),
                new Runnable() {
                    @Override
                    public void run() {
                        slideButtonsOutAndTransitionTo(new GameScreen());

                    }
                });
        this.playButton = playButton;
        playButton.setScaling(Scaling.fit);
        buttonTable.add(playButton)
                .space(Value.percentHeight(0.2f))
                .minHeight(50)
                .padLeft(20)
                .padRight(20)
                .minWidth(100);

        buttonTable.row();

        final Button planesButton = new Button("",
                new Texture(Resources.btn_planes_up_img),
                new Texture(Resources.btn_planes_down_img),
                new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new PlaneShowcaseScreen());
                    }
                });
        this.planesButton = planesButton;
        planesButton.setScaling(Scaling.fit);
        buttonTable.add(planesButton)
                .space(Value.percentHeight(0.2f))
                .minHeight(50)
                .padLeft(20)
                .padRight(20)
                .minWidth(100);


        buttonTable.row();

        final Button optionsButton = new Button("",
                new Texture(Resources.btn_opts_up_img),
                new Texture(Resources.btn_opts_down_img),
                new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new OptionsScreen());
                    }
                });

        this.optionsButton = optionsButton;
        optionsButton.setScaling(Scaling.fit);
        buttonTable.add(optionsButton)
                .space(Value.percentHeight(0.2f))
                .padBottom(15)
                .padLeft(20)
                .padRight(20)
                .minHeight(50)
                .minWidth(100);

        table.add(buttonTable);

    }

    public void slideButtonsOutAndTransitionTo(final Screen next){
        MoveToAction movePlay = new MoveToAction();
        movePlay.setDuration(0.7f);
        movePlay.setInterpolation(Interpolation.exp10);
        movePlay.setPosition(Gdx.graphics.getWidth() + playButton.getWidth(), playButton.getY());
        movePlay.setActor(playButton);

        MoveToAction movePlanes = new MoveToAction();
        movePlanes.setDuration(0.5f);
        movePlanes.setInterpolation(Interpolation.exp10);
        movePlanes.setPosition(Gdx.graphics.getWidth() + planesButton.getWidth(), planesButton.getY());
        movePlanes.setActor(planesButton);

        MoveToAction moveOptions = new MoveToAction();
        moveOptions.setDuration(0.3f);
        moveOptions.setInterpolation(Interpolation.exp10);
        moveOptions.setPosition(Gdx.graphics.getWidth() + optionsButton.getWidth(), optionsButton.getY());
        moveOptions.setActor(optionsButton);

        MoveToAction moveText1 = new MoveToAction();
        moveText1.setDuration(0.4f);
        moveText1.setInterpolation(Interpolation.pow2);
        moveText1.setPosition(-text1.getImageWidth()*2, text1.getY());
        moveText1.setActor(text1);

        MoveToAction moveText2 = new MoveToAction();
        moveText2.setDuration(0.4f);
        moveText2.setInterpolation(Interpolation.pow2);
        moveText2.setPosition(Gdx.graphics.getWidth() + text2.getImageWidth(), text2.getY());
        moveText2.setActor(text2);

        logo.setOrigin(Align.center);
        ParallelAction moveLogo = Actions.parallel(
                Actions.scaleBy(-0.5f, -0.5f, 0.4f, Interpolation.pow2),
                Actions.fadeOut(0.4f, Interpolation.pow2));
        moveLogo.setActor(logo);

        AlphaAction fadeOut = Actions.fadeOut(0.2f, Interpolation.pow2Out);
        fadeOut.setActor(stage.getRoot());

        stage.addAction(
                Actions.sequence(
                        Actions.parallel(moveOptions, movePlay, movePlanes, moveText1, moveText2, moveLogo),
                        fadeOut,
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(next);
                            }
                        })
                ));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(139f / 255f, 166f / 255f, 177f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        camera.setToOrtho(false);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
