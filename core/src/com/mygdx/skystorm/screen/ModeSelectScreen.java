package com.mygdx.skystorm.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.data.Resources;
import com.mygdx.skystorm.screen.ui.ShadowTextButton;

/**
 * Allows the player to select a mode for gameplay
 *  Modes are:
 *      - Campaign
 *      - Arcade
 */
public class ModeSelectScreen extends ActionScreen {

    private ShadowTextButton arcadeModeButton, campaignModeButton;

    public ModeSelectScreen(SkyStorm game) {
        super(game);
        createMenuButtons();
    }

    public void createMenuButtons(){

        Table root = new Table();
        root.setFillParent(true);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Resources.menu_font));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font =  generator.generateFont(parameter);
        generator.dispose();
        style.fontColor = new Color(0.9f, 0.92f, 0.36f,1);


        arcadeModeButton = new ShadowTextButton("Arcade", style);
        root.add(arcadeModeButton);

        root.row().pad(20, 0, 20, 0);

        campaignModeButton = new ShadowTextButton("Campaign", style);
        root.add(campaignModeButton);
        stage.addActor(root);
    }
}
