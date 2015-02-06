package com.mygdx.skystorm.screen;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.data.Resources;
import com.mygdx.skystorm.screen.ui.Button;

/**
 * Allows the player to select a mode for gameplay
 *  Modes are:
 *      - Campaign
 *      - Arcade
 */
public class ModeSelectScreen extends ActionScreen {

    private Button arcadeModeButton, campaignModeButton;

    public ModeSelectScreen(SkyStorm game) {
        super(game);
        createMenuButtons();
    }

    public void createMenuButtons(){
        arcadeModeButton = new Button("Arcade",
                new Texture(Resources.btn_play_up_img),
                new Texture(Resources.btn_play_down_img),
                new Runnable() {
            @Override
            public void run() {
//                game.setScreen(new ArcadeGameScreen(game));
            }
        });

        campaignModeButton = new Button("Campaign",
                new Texture(Resources.btn_play_up_img),
                new Texture(Resources.btn_play_down_img),
                new Runnable() {
                    @Override
                    public void run() {
//                game.setScreen(new ArcadeGameScreen(game));
                    }
                });

    }
}
