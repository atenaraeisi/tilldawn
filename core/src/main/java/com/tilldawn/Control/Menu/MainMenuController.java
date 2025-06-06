package com.tilldawn.Control.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.Menu.*;
import com.tilldawn.View.ScoreboardView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            if (view.getPregameButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            if (view.getExitButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EntryMenuView(new EntryMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            if (view.getSettingsButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingsMenuView(new SettingsMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            if (view.getProfileButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            if (view.getHintsButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new talentMenuView(new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            if (view.getScoreboardButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
                Main.getMain().setScreen(new ScoreboardView(skin));
            }
        }
    }
}
