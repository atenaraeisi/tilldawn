package com.tilldawn.Control.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Control.data.UserDataSQL;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.Menu.EntryMenuView;
import com.tilldawn.View.Menu.MainMenuView;
import com.tilldawn.View.Menu.RegisterMenuView;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleRegisterMenuButtons() {
        if (view != null) {
            if (view.getGoBackButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EntryMenuView(new EntryMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            else if (view.getRegisterButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                String selectedQuestion = view.getSelectedQuestion();
                String answer = view.getAnswerField().getText();

                if (username == null || username.trim().isEmpty()) {
                    view.showError("Please enter your username");
                    view.getRegisterButton().setChecked(false);
                    return;
                }

                if (password == null || password.trim().isEmpty()) {
                    view.showError("Please enter your password");
                    view.getRegisterButton().setChecked(false);
                    return;
                }

                if (Game.isUsernameExist(username)) {
                    view.showError("Username is already exists!");
                    view.getRegisterButton().setChecked(false);
                    return;
                }

                if (!User.getPasswordPattern().matcher(password).matches()) {
                    view.showError("Your password is weak!");
                    view.getRegisterButton().setChecked(false);
                    return;
                }
                if (answer == null || answer.trim().isEmpty()) {
                    view.showError("Please enter your answer");
                    view.getRegisterButton().setChecked(false);
                    return;
                }
                User user = new User(username, password, selectedQuestion, answer);
                Game.addUser(user);
                UserDataSQL.getInstance().addUser(user);
                Game.setCurrentUser(user);
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
        }
    }
}
