package com.mygdx.pixelpilot.screen.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.data.Config;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.ai.AISpawnEvent;
import com.mygdx.pixelpilot.event.events.game.GamePauseEvent;
import com.mygdx.pixelpilot.event.events.game.GameResumeEvent;
import com.mygdx.pixelpilot.event.events.game.WaveSpawnEvent;
import com.mygdx.pixelpilot.event.events.screen.MenuOpenEvent;
import com.mygdx.pixelpilot.screen.menu.PauseMenu;
import com.mygdx.pixelpilot.screen.ui.ShadowImage;
import com.mygdx.pixelpilot.screen.ui.ShadowImageButton;
import net.engio.mbassy.listener.Handler;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class HUD extends Stage {

    private Table table;
    private Label lives;
    private ShadowImage livesIcon;
    private ShadowImage scoreIcon;
    private Label score;
    private ShadowImageButton pauseButton;

    private final int MARKER_LAYER = 0;
    private final int UI_LAYER = 1;
    private final int LABEL_LAYER = 2;

    public HUD() {
        super(new ExtendViewport(Config.NativeView.width, Config.NativeView.height, new OrthographicCamera()));
        Gdx.input.setInputProcessor(this);
        Events.getBus().subscribe(this);

        table = new Table();
        table.setFillParent(true);
        table.setZIndex(UI_LAYER);
        table.top().left();
        addHUDComponents();

        this.addActor(table);
    }

    private void addHUDComponents() {

        ShadowImage.ShadowImageStyle imageStyle = new ShadowImage.ShadowImageStyle();
        imageStyle.shadowColor = new Color(0, 0, 0, 1);
        imageStyle.shadowDepth = 5;

        livesIcon = new ShadowImage(imageStyle);
        Texture planeIcon = new Texture(Assets.image.plane);
        livesIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(planeIcon)));

        scoreIcon = new ShadowImage(imageStyle);
        Texture trophy = new Texture(Assets.image.trophy);
        scoreIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(trophy)));

        ShadowImageButton.ShadowImageButtonStyle pauseStyle = new ShadowImageButton.ShadowImageButtonStyle();
        pauseStyle.shadowColor = new Color(0, 0, 0, 1);
        pauseStyle.shadowDepth = 5;
        Texture pause = new Texture(Assets.image.pause_button);
        pauseStyle.imageUp = new TextureRegionDrawable(new TextureRegion(pause));
        pauseButton = new ShadowImageButton(pauseStyle);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Events.getBus().publish(new MenuOpenEvent(new PauseMenu()));
                Events.getBus().publish(new GamePauseEvent());
            }
        });

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Assets.manager.get("hud-font");
        lives = new Label("3", style);
        score = new Label("0", style);

        table.add(livesIcon).size(45, 45).pad(10);
        table.add(lives);
        table.add(scoreIcon).size(38, 45).pad(10, 30, 10, 10);
        table.add(score);
        table.add(pauseButton).size(50, 50).right().pad(10).expandX();
    }

    @Handler
    public void onWaveSpawn(WaveSpawnEvent event){
        String message = event.getWave().message;

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Assets.manager.get("wave-font");

        final Label label = new Label(message, style);
        label.setZIndex(LABEL_LAYER);
        label.setPosition(Config.NativeView.width / 2, Config.NativeView.height / 2, Align.center);
        this.addActor(label);

        label.addAction(sequence(
                delay(1),
                fadeOut(0.35f),
                run(new Runnable() {
                    @Override
                    public void run() {
                        label.remove();
                    }
                })

        ));

    }

    @Handler
    public void onAISpawn(AISpawnEvent event){
        PlaneMarker marker = new PlaneMarker(event.getPlane());
        marker.setZIndex(MARKER_LAYER);
        this.addActor(marker);
    }

    @Handler
    public void onResume(GameResumeEvent event) {
        Gdx.input.setInputProcessor(this);
    }

}
