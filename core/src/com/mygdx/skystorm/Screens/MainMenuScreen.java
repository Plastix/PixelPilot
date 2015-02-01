package com.mygdx.skystorm.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.skystorm.Effects.Cloud;
import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.Utils.Utils;

import java.util.ArrayList;

public class MainMenuScreen implements Screen {
    final SkyStorm game;
    OrthographicCamera camera;
    Stage stage;
    public MainMenuScreen(SkyStorm game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        ScreenViewport view = new ScreenViewport(camera);
        stage = new Stage(view);

        for(int i = 0; i < 15; i++)
            stage.addActor(new Cloud(
                    Utils.randomFloat(0, Gdx.graphics.getWidth()),
                    Utils.randomFloat(0, Gdx.graphics.getHeight()),
                    75, 75, 5));

        Gdx.input.setInputProcessor(stage);

        createMenuGraphics();

    }

    private void createMenuGraphics() {

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // create title logo header
        Table logoTable = new Table();

        Image logo = new Image(new Texture(Gdx.files.internal("menu_logo.png")));
        Image text1 = new Image(new Texture(Gdx.files.internal("menu_txt_pixel.png")));
        Image text2 = new Image(new Texture(Gdx.files.internal("menu_txt_pilot.png")));
        logo.setScaling(Scaling.fit);
        text1.setScaling(Scaling.fit);
        text2.setScaling(Scaling.fit);
        logoTable.add(text1).padRight(15).padLeft(10).fill().expand();
        logoTable.add(logo).fill().expand();
        logoTable.add(text2).padLeft(15).padRight(10).fill().expand();
        float scale = 0.6f;
        table.top();
        table.add(logoTable)
                .prefSize((logo.getWidth() + text1.getWidth() + text2.getWidth()) * scale, logo.getHeight() * scale)
                .expandX()
                .padTop(20)
                .space(45);

        table.row();

        Table buttonTable = new Table();
        float btnScale = scale-0.1f;

        // create the buttons
        Button playButton = createButton(
                Gdx.files.internal("menu_button_play.png"),
                Gdx.files.internal("menu_button_play_down.png"),
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.setScreen(new GameScreen());
                    }
                });
        buttonTable.add(playButton).space(15)
                .size(playButton.getWidth() * btnScale, playButton.getHeight() * btnScale);

        buttonTable.row();

        Button planesButton = createButton(
                Gdx.files.internal("menu_button_planes.png"),
                Gdx.files.internal("menu_button_planes_down.png"),
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.setScreen(new OptionsScreen());
                    }
                });
        buttonTable.add(planesButton).space(15)
                .size(planesButton.getWidth() * btnScale, planesButton.getHeight() * btnScale);

        buttonTable.row();

        Button optionsButton = createButton(
                Gdx.files.internal("menu_button_options.png"),
                Gdx.files.internal("menu_button_options_down.png"),
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.setScreen(new PlaneShowcaseScreen());
                    }
                });
        buttonTable.add(optionsButton).space(15)
                .size(optionsButton.getWidth() * btnScale, optionsButton.getHeight() * btnScale);

        table.add(buttonTable);

    }

    private Button createButton(FileHandle up, FileHandle down, ChangeListener lstnr) {
        Image upImg = new Image(new Texture(up));
        upImg.setScaling(Scaling.fit);
        Image downImg = new Image(new Texture(down));
        downImg.setScaling(Scaling.fit);
        Button.ButtonStyle style = new Button.ButtonStyle(upImg.getDrawable(),
                downImg.getDrawable(),
                upImg.getDrawable());
        Button button = new Button(style);
        button.addListener(lstnr);
        return button;
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
