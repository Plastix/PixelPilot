package com.mygdx.skystorm.screen;

import com.mygdx.skystorm.SkyStorm;
import com.mygdx.skystorm.screen.ui.Button;

/**
 * Allows the player to select a mode for gameplay
 *  Modes are:
 *      - Campaign
 *      - Arcade
 */
public class ModeSelectScreen extends ActionScreen {

    private Button arcadeModeButton, campaignModeButton;

    ModeSelectScreen(SkyStorm game) {
        super(game);
    }
}
