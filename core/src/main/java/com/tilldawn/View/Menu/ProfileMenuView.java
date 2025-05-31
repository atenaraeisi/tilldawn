package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.ProfileMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import org.w3c.dom.Text;

public class ProfileMenuView implements Screen {
    private Stage stage;
    private Table table;
    private Skin skin;
    private ProfileMenuController controller;

    private final Label titleLabel;
    private Label username;
    private Label score;
    private final TextButton changeUsernameButton;
    private final TextField newUsername;
    private final TextButton changePasswordButton;
    private final TextField newPassword;
    private final TextButton deleteAccountButton;
    private final TextButton changeAvatarButton;
    private final TextButton backButton;
    private final Label errorLabel;
    private Image selectedAvatarImage;
    private final TextureRegionDrawable[] avatarDrawables = GameAssetManager.getGameAssetManager().getAvatarDrawables();

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.table = new Table();
        this.titleLabel = new Label("P r o f i l e    M e n u", skin.get("title", Label.LabelStyle.class));
        this.newPassword = new TextField("", skin);
        newPassword.setMessageText("Enter New Password");
        this.newUsername = new TextField("", skin);
        newUsername.setMessageText("Enter New Username");
        this.changeUsernameButton = new TextButton("Change Username", skin);
        this.changePasswordButton = new TextButton("Change Password", skin);
        this.deleteAccountButton = new TextButton("Delete Account", skin);
        this.changeAvatarButton = new TextButton("Choose Avatar", skin);
        this.table = new Table();
        this.errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        this.backButton = new TextButton("Back", skin);
        if (Game.getCurrentUser() != null) {
            this.username = new Label("Username: " + Game.getCurrentUser().getUsername(), skin);
            this.score = new Label("Score: " + Game.getCurrentUser().getScore(), skin);
            this.selectedAvatarImage = Game.getCurrentUser().getAvatar();

        } else {
            username = new Label("Name: Guest", skin);
            this.score = new Label("Score: 0", skin);
            this.selectedAvatarImage = new Image(avatarDrawables[0]);
        }
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        stage.addActor(table);

        selectedAvatarImage.setScaling(Scaling.fit);
        selectedAvatarImage.setSize(200, 200);

        Table infoRow = new Table();
        infoRow.add(username).pad(10, 20, 50 , 20).row();
        infoRow.add(score).pad(10);

        Table profileRow = new Table();
        profileRow.add(selectedAvatarImage).size(200).padRight(20);
        profileRow.add(infoRow);


        Table avatarRow = new Table();
        for (int i = 0; i < avatarDrawables.length; i++) {
            final int index = i;
            ImageButton avatarButton = new ImageButton(avatarDrawables[i]);
            avatarButton.getImage().setScaling(Scaling.fit);
            avatarButton.getImage().setSize(64, 64);

            avatarButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    selectedAvatarImage.setDrawable(avatarDrawables[index]);
                    controller.setAvatar(selectedAvatarImage);
                }
            });

            avatarRow.add(avatarButton).size(64).pad(5);
        }


        Table fieldRow = new Table();
        fieldRow.add(newUsername).size(450, 100).padRight(40);
        fieldRow.add(newPassword).size(450, 100);

        Table buttonsRow = new Table();
        buttonsRow.add(changeUsernameButton).size(450, 100).padRight(40);
        buttonsRow.add(changePasswordButton).size(450, 100);

        Table buttonsRow2 = new Table();
        buttonsRow2.add(deleteAccountButton).size(450, 100).padRight(40);
        buttonsRow2.add(changeAvatarButton).size(450, 100);


        table.add(titleLabel).pad(10).padBottom(10).row();
        table.add(errorLabel).pad(10).padBottom(20).row();
        table.add(profileRow).pad(10).row();
        table.add(avatarRow).colspan(2).pad(10).padBottom(40).row();
        table.add(fieldRow).pad(10).row();
        table.add(buttonsRow).pad(10).row();
        table.add(buttonsRow2).pad(10).row();
        table.add(backButton).size(450, 100);
    }

    @Override
    public void render(float v) {
        MainMenuView.initialRender(stage);
        if (Game.getCurrentUser() != null) {
            username.setText("Username: " + Game.getCurrentUser().getUsername());
            score.setText("Score: " + Game.getCurrentUser().getScore());
            selectedAvatarImage = Game.getCurrentUser().getAvatar();

        }
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleProfileMenuButtons();
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public TextButton getChangeAvatarButton() {
        return changeAvatarButton;
    }

    public TextField getNewPassword() {
        return newPassword;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public TextField getNewUsername() {
        return newUsername;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
    public void showError(String message) {
        errorLabel.setText(message);
    }

}
