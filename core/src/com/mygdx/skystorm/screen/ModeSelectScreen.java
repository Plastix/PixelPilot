package com.mygdx.skystorm.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
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

        Table root = new Table();
        root.setFillParent(true);

        arcadeModeButton = new Button("Arcade",
                new Texture(Resources.menu_button),
                new Texture(Resources.menu_button_down),
                new Runnable() {
            @Override
            public void run() {
//                game.setScreen(new ArcadeGameScreen(game));
            }
        });
        arcadeModeButton.setScaling(Scaling.fit);
        root.add(arcadeModeButton).pad(20);

        campaignModeButton = new Button("Campaign",
                new Texture(Resources.menu_button),
                new Texture(Resources.menu_button_down),
                new Runnable() {
                    @Override
                    public void run() {
//                game.setScreen(new ArcadeGameScreen(game));
                    }
                });
        root.add(campaignModeButton);
        campaignModeButton.setScaling(Scaling.fit);
        stage.addActor(root);
    }
}
