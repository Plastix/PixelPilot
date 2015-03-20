package com.mygdx.pixelpilot.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.pixelpilot.PixelPilot;
import com.mygdx.pixelpilot.data.Assets;

/**
 * Allows the player to select a mode for gameplay
 *  Modes are:
 *      - Campaign
 *      - Arcade
 */
public class ModeSelectScreen extends MenuScreen {

    private TextButton arcadeModeButton, campaignModeButton;

    public ModeSelectScreen(PixelPilot game) {
        super(game);
        createMenuButtons();
    }

    public void createMenuButtons(){

        Table root = new Table();
        root.setFillParent(true);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.font.pixel));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        parameter.color = new Color(0.9f, 0.92f, 0.36f,1);
        parameter.shadowColor = new Color(0.72f,0.74f,0.3f,1);
        parameter.shadowOffsetY = 8;


        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font =  generator.generateFont(parameter);
        generator.dispose();

        arcadeModeButton = new TextButton("Arcade", style);
        root.add(arcadeModeButton);
        root.row().pad(20, 0, 20, 0);

        campaignModeButton = new TextButton("Campaign", style);
        root.add(campaignModeButton);
        stage.addActor(root);
    }
}
