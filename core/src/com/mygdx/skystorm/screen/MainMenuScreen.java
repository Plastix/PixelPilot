package com.mygdx.skystorm.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.effects.Cloud;
import com.mygdx.skystorm.screen.ui.Button;
import com.mygdx.skystorm.util.Utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen implements Screen {
    private static final String menu_logo = "menu_logo.png";
    private static final String menu_text_pixel = "menu_txt_pixel.png";
    private static final String menu_text_pilot = "menu_txt_pilot.png";
    private static final String btn_play_up_img = "menu_button_play.png";
    private static final String btn_play_down_img = "menu_button_play_down.png";
    private static final String btn_opts_up_img = "menu_button_options.png";
    private static final String btn_opts_down_img = "menu_button_options_down.png";
    private static final String btn_planes_up_img = "menu_button_planes.png";
    private static final String btn_planes_down_img = "menu_button_planes_down.png";
    final SkyStorm game;
    OrthographicCamera camera;
    Stage stage;

    public MainMenuScreen(SkyStorm game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        ScreenViewport view = new ScreenViewport(camera);
        stage = new Stage(view);

        for (int i = 0; i < 15; i++) {
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

        Image logo = new Image(new Texture(menu_logo));
        Image text1 = new Image(new Texture(menu_text_pixel));
        Image text2 = new Image(new Texture(menu_text_pilot));
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
                new Texture(btn_play_up_img),
                new Texture(btn_play_down_img),
                new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen());
                    }
                });
        playButton.setScaling(Scaling.fit);
        buttonTable.add(playButton)
                .space(Value.percentHeight(0.2f))
                .minHeight(50)
                .padLeft(20)
                .padRight(20)
                .minWidth(100);

        buttonTable.row();

        final Button planesButton = new Button("",
                new Texture(btn_planes_up_img),
                new Texture(btn_planes_down_img),
                new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new PlaneShowcaseScreen());
                    }
                });
        planesButton.setScaling(Scaling.fit);
        buttonTable.add(planesButton)
                .space(Value.percentHeight(0.2f))
                .minHeight(50)
                .padLeft(20)
                .padRight(20)
                .minWidth(100);


        buttonTable.row();

        final Button optionsButton = new Button("",
                new Texture(btn_opts_up_img),
                new Texture(btn_opts_down_img),
                new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new OptionsScreen());
                    }
                });
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
