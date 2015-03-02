package com.mygdx.pixelpilot.event.events.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.pixelpilot.event.events.GameEvent;

public class GameResumeEvent extends GameEvent {

    private Stage stage;

    public GameResumeEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
