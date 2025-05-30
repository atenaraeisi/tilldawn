package com.tilldawn.Control.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Control.UserDataSQL;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.Menu.EntryMenuView;
import com.tilldawn.View.Menu.MainMenuView;
import com.tilldawn.View.Menu.LoginMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            String username = view.getUsernameField().getText();
            String password = view.getPasswordField().getText();
            String answer = view.getSecurityAnswerField().getText();
            String newPassword = view.getNewPasswordField().getText();

            if (view.getGoBackButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EntryMenuView(new EntryMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            else if (view.getLoginButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();

                User user = UserDataSQL.getInstance().getUser(username);
                if (user == null) {
                    view.showError("Username not found");
                    view.getLoginButton().setChecked(false);
                    return;
                }
                if (!user.getPassword().equals(password)) {
                    view.showError("Wrong password");
                    view.getLoginButton().setChecked(false);
                    return;
                }
                Game.setCurrentUser(user);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            else if (view.getForgetPasswordButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                if (username == null || username.trim().isEmpty()) {
                    view.showError("Please enter your username!");
                    view.getForgetPasswordButton().setChecked(false);
                    return;
                }
                User user = UserDataSQL.getInstance().getUser(username);
                if (user == null) {
                    view.showError("User with this username not found");
                    view.getForgetPasswordButton().setChecked(false);
                    return;
                }
                view.showSecurityQuestion(user.getSelectedQuestion());
                view.getForgetPasswordButton().setChecked(false);
            }
            else if (view.getConfirmResetButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                if (username == null || username.trim().isEmpty()) {
                    view.showError("Please enter your username!");
                    view.getConfirmResetButton().setChecked(false);
                    return;
                }
                User user = UserDataSQL.getInstance().getUser(username);
                if (user == null) {
                    view.showError("User with this username not found");
                    view.getConfirmResetButton().setChecked(false);
                    return;
                }
                if (answer == null || answer.trim().isEmpty()) {
                    view.showError("Please enter your security answer!");
                    view.getConfirmResetButton().setChecked(false);
                    return;
                }
                if (newPassword == null || newPassword.trim().isEmpty()) {
                    view.showError("Please enter your new password!");
                    view.getConfirmResetButton().setChecked(false);
                    return;
                }
                if (!User.getPasswordPattern().matcher(password).matches()) {
                    view.showError("Your password is weak!");
                    view.getConfirmResetButton().setChecked(false);
                    return;
                }
                if (!user.getAnswer().equals(answer)) {
                    view.showError("Wrong security answer");
                    view.getConfirmResetButton().setChecked(false);
                    return;
                }
                user.setPassword(newPassword);
                UserDataSQL.getInstance().updatePassword(username, newPassword);
                Game.setCurrentUser(user);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));

            }
        }
    }
}
