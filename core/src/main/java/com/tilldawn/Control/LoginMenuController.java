package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.EntryMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PreGameMenuView;
import com.tilldawn.View.LoginMenuView;

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
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EntryMenuView(new EntryMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            else if (view.getLoginButton().isChecked()) {

                if (!Game.isUsernameExist(username)) {
                    view.showError("Username not found");
                    view.getLoginButton().setChecked(false);
                    return;
                }
                if (!Game.isPasswordCorrect(username, password)) {
                    view.showError("Wrong password");
                    view.getLoginButton().setChecked(false);
                    return;
                }
                Game.setCurrentUser(Game.getUser(username));
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
            else if (view.getForgetPasswordButton().isChecked()) {
                if (username == null || username.trim().isEmpty()) {
                    view.showError("Please enter your username!");
                    view.getForgetPasswordButton().setChecked(false);
                    return;
                }
                User user = Game.getUser(username);
                if (user == null) {
                    view.showError("User with this username not found");
                    view.getForgetPasswordButton().setChecked(false);
                    return;
                }
                view.showSecurityQuestion(user.getSelectedQuestion());
                view.getForgetPasswordButton().setChecked(false);
            }
            else if (view.getConfirmResetButton().isChecked()) {
                if (username == null || username.trim().isEmpty()) {
                    view.showError("Please enter your username!");
                    view.getConfirmResetButton().setChecked(false);
                    return;
                }
                User user = Game.getUser(username);
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
                Game.setCurrentUser(user);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));

            }
        }
    }
}
