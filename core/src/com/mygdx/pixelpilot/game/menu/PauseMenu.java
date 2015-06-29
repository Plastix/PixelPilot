package com.mygdx.pixelpilot.game.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.mygdx.pixelpilot.DependentBuilder;
import com.mygdx.pixelpilot.game.ui.ShadowImageButton;

public class PauseMenu extends Menu {

    private final PauseMenu instance;

    private PauseMenu() {
        this.instance = this;

        this.dimBackground();
        table.top().center();

        addPauseLabel();
        table.row().expandX();
        addOptionsButton();
        addResumeButton();
        addRestartButton();
        addMenuButton();
    }

    public static class Loader extends DependentBuilder<PauseMenu> {

        public Loader() {
            super();
        }

        @Override
        public PauseMenu build() {
            return new PauseMenu();
        }
    }

    private void addPauseLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Assets.manager.get(Assets.Fonts.options_menu, BitmapFont.class);

        Label pause = new Label("Paused", style);
        table.add(pause).colspan(4).pad(0, 0, 40, 0);
    }


    private void addResumeButton() {
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture resumeTexture = Assets.manager.get(Assets.Images.play_icon, Texture.class);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(resumeTexture));
        style.shadowDepth = 8;
        style.shadowColor = new Color(0.15f, 0.5f, 0.1f, 1);
        ShadowImageButton resume = new ShadowImageButton(style);
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Events.getBus().publish(new MenuCloseEvent());
                Events.getBus().publish(new GameResumeEvent());
            }
        });
        table.add(resume).size(150, 150).right().padRight(25);
    }

    private void addRestartButton() {
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture restartTexture = Assets.manager.get(Assets.Images.restart_icon, Texture.class);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(restartTexture));
        style.shadowDepth = 8;
        style.shadowColor = new Color(0.4f, 0.09f, 0.09f, 1);
        ShadowImageButton restart = new ShadowImageButton(style);
        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Restart!");
            }
        });
        table.add(restart).size(150, 150).left().padLeft(25);
    }

    private void addOptionsButton() {
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture wrench = Assets.manager.get(Assets.Images.wrench, Texture.class);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(wrench)).tint(new Color(0.9f, 0.92f, 0.36f, 1));
        style.shadowDepth = 7;
        style.shadowColor = new Color(0.6f, 0.6f, 0.2f, 1);
        ShadowImageButton options = new ShadowImageButton(style);
        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("Options!");
                Events.getBus().publish(new MenuOpenEvent(new OptionsMenu.Loader()));

            }
        });
        table.add(options).size(100, 100).right();
    }

    private void addMenuButton() {
        ShadowImageButton.ShadowImageButtonStyle style = new ShadowImageButton.ShadowImageButtonStyle();
        Texture menuTexure = Assets.manager.get(Assets.Images.menu_icon, Texture.class);
        style.imageUp = new TextureRegionDrawable(new TextureRegion(menuTexure)).tint(new Color(0.9f, 0.92f, 0.36f, 1));
        style.shadowDepth = 7;
        style.shadowColor = new Color(0.6f, 0.6f, 0.2f, 1);
        ShadowImageButton menu = new ShadowImageButton(style);
        menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Menu!");
                Events.getBus().publish(new ScreenChangeEvent(new MenuScreen.Builder().setMenu(new MainMenu.Builder())));
            }
        });
        table.add(menu).size(100, 100).left();
    }


    @Override
    public void dispose() {

    }


}
