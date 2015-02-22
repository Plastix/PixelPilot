package com.mygdx.skystorm.screen.game.hud;

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
import com.mygdx.skystorm.data.Resources;
import com.mygdx.skystorm.event.EventHandler;
import com.mygdx.skystorm.event.Events;
import com.mygdx.skystorm.event.Listener;
import com.mygdx.skystorm.event.events.PlaneSpawnEvent;
import com.mygdx.skystorm.event.events.player.PlayerDeathEvent;
import com.mygdx.skystorm.event.events.player.PlayerSpawnEvent;
import com.mygdx.skystorm.plane.Plane;
import com.mygdx.skystorm.screen.ui.ShadowImage;
import com.mygdx.skystorm.screen.ui.ShadowImageButton;
import com.mygdx.skystorm.screen.ui.ShadowLabel;


public class HUD extends Stage implements Listener {

    private Table table;
    private ShadowLabel lives;
    private ShadowImage livesIcon;
    private ShadowImage scoreIcon;
    private ShadowLabel score;
    private ShadowImageButton pauseButton;
    private Plane player;

    public HUD(){
        this.setViewport(new ExtendViewport(960, 540, new OrthographicCamera()));
        Gdx.input.setInputProcessor(this);
        Events.register(this);

        table = new Table();
        table.setFillParent(true);
        table.top().left();
        addHUDComponents();

        this.addActor(table);
    }


    private void addHUDComponents(){

        ShadowImage.ShadowImageStyle imageStyle = new ShadowImage.ShadowImageStyle();
        imageStyle.shadowColor = new Color(0,0,0,1);
        imageStyle.shadowDepth = 5;

        livesIcon = new ShadowImage(imageStyle);
        Texture planeIcon = new Texture(Resources.plane);
        livesIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(planeIcon)));

        scoreIcon = new ShadowImage(imageStyle);
        Texture trophy = new Texture(Resources.trophy);
        scoreIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(trophy)));

        ShadowImageButton.ShadowImageButtonStyle pauseStyle = new ShadowImageButton.ShadowImageButtonStyle();
        pauseStyle.shadowColor = new Color(0,0,0,1);
        pauseStyle.shadowDepth = 5;
        Texture pause = new Texture(Resources.pause_button);
        pauseStyle.imageUp = new TextureRegionDrawable(new TextureRegion(pause));
        pauseButton = new ShadowImageButton(pauseStyle);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Pause Clicked!");
            }
        });

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Resources.menu_font));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 76;

        ShadowLabel.ShadowLabelStyle style = new ShadowLabel.ShadowLabelStyle();
        style.font =  generator.generateFont(parameter);
        generator.dispose();
        style.fontColor = new Color(1,1,1,1);
        style.shadowDepth = 5;
        style.shadowColor = new Color(0,0,0,1);

        lives = new ShadowLabel("x3", style);
        score = new ShadowLabel("0", style);

        table.add(livesIcon).size(45,45).pad(10);
        table.add(lives);
        table.add(scoreIcon).size(38,45).pad(10,30,10,10);
        table.add(score);

        table.add(pauseButton).size(50,50).right().pad(10).expandX();
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnEvent event) {
        player = event.getPlane();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        player = null;
    }

    @EventHandler
    public void onPlaneSpawn(PlaneSpawnEvent event){
        this.addActor(new PlaneMarker(event.getPlane(), player));
    }

}