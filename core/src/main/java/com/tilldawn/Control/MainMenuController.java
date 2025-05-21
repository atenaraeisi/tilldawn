package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Pregame;
import com.tilldawn.View.EntryMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PreGameMenuView;
import com.tilldawn.View.SettingsMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            if (view.getPregameButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if (view.getExitButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EntryMenuView(new EntryMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            if (view.getSettingsButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingsMenuView(new SettingsMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
        }
    }
}
