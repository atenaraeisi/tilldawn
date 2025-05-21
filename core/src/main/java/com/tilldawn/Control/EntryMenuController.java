package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.*;

public class EntryMenuController {
    private EntryMenuView view;

    public void setView(EntryMenuView view) {
        this.view = view;
    }

    public void handleEntryMenuButtons() {
        if (view != null) {
            if (view.getGuestButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            else if (view.getSignUpButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(),  new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            else if (view.getLoginButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }

            else if (view.getExitButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Gdx.app.exit();
            }
        }
    }
}
