package com.tilldawn.Control.Menu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Control.data.UserDataSQL;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.Menu.EntryMenuView;
import com.tilldawn.View.Menu.MainMenuView;
import com.tilldawn.View.Menu.ProfileMenuView;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleProfileMenuButtons() {
        if (view != null) {
            String newUsername = view.getNewUsername().getText();
            String newPassword = view.getNewPassword().getText();
            if (view.getBackButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }

                if (view.getChangePasswordButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                User user = Game.getCurrentUser();
                if (user == null) {
                    view.showError("You are guest!");
                    view.getChangePasswordButton().setChecked(false);
                    return;
                }
                if (newPassword == null || newPassword.trim().isEmpty()) {
                    view.showError("Please enter your new password!");
                    view.getChangePasswordButton().setChecked(false);
                    return;
                }
                if (!User.getPasswordPattern().matcher(newPassword).matches()) {
                    view.showError("Your password is weak!");
                    view.getChangePasswordButton().setChecked(false);
                    return;
                }
                user.setPassword(newPassword);
                view.showError("Password changed!");
                view.getChangePasswordButton().setChecked(false);
            }

            if (view.getChangeUsernameButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                User user = Game.getCurrentUser();
                if (user == null) {
                    view.showError("You are guest!");
                    view.getChangeUsernameButton().setChecked(false);
                    return;
                }
                if (newUsername == null || newUsername.trim().isEmpty()) {
                    view.showError("Please enter your new username!");
                    view.getChangeUsernameButton().setChecked(false);
                    return;
                }
                if (Game.isUsernameExist(newUsername)) {
                    view.showError("Username is already exists!");
                    view.getChangeUsernameButton().setChecked(false);
                    return;
                }
                user.setUsername(newUsername);
                view.showError("Username changed!");
                view.getChangeUsernameButton().setChecked(false);
            }

            if (view.getDeleteAccountButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                User user = Game.getCurrentUser();
                if (user == null) {
                    view.showError("You are guest!");
                    view.getDeleteAccountButton().setChecked(false);
                    return;
                }
                UserDataSQL.getInstance().deleteUser(user.getUsername());
                Main.getMain().setScreen(new EntryMenuView(new EntryMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getChangeAvatarButton().isChecked()) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                changeAvatar();
                view.getChangeAvatarButton().setChecked(false);
            }

        }
    }
    public void setAvatar(Image avatar) {
       User currentUser = Game.getCurrentUser();
       if (currentUser == null) {
           view.getChangePasswordButton().setChecked(false);
           return;
       }
       currentUser.setAvatar(avatar);
    }

    public void changeAvatar() {
        new Thread(() -> {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Select Avatar Image");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Images", "png"));

            int result = fileChooser.showOpenDialog(null);
            if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();

                Gdx.app.postRunnable(() -> {
                    try {
                        FileHandle fileHandle = new FileHandle(path);
                        Texture texture = new Texture(fileHandle);
                        Image image = new Image(texture);
                        if (Game.getCurrentUser() != null) {
                            Game.getCurrentUser().setAvatar(image);
                        } else view.showError("Guests cannot change avatar!");
                        view.showError("Avatar updated successfully.");
                    } catch (Exception e) {
                        view.showError("Failed to load image.");
                        e.printStackTrace();
                    }
                });
            }
        }).start();
    }


}
