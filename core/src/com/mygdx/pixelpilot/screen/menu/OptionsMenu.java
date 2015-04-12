package com.mygdx.pixelpilot.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelpilot.data.Assets;
import com.mygdx.pixelpilot.event.Events;
import com.mygdx.pixelpilot.event.events.screen.MenuCloseEvent;

public class OptionsMenu extends Menu {

    private FreeTypeFontGenerator generator;
    private OptionsMenu instance;

    public OptionsMenu() {
        this.instance = this;
        this.dimBackground();

        generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.font.pixel));
        addLabel();
        table.row();
        addBackButton();
    }


    private void addLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Assets.manager.get("label-font");

        Label options = new Label("Options", style);
        table.add(options);
    }

    private void addBackButton() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = Assets.manager.get("label-font");

        TextButton back = new TextButton("Back", style);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Events.getBus().publish(new MenuCloseEvent());
            }
        });
        table.add(back);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.generator.dispose();
    }
}
