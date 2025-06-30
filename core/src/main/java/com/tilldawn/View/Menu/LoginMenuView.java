package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.LoginMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class LoginMenuView implements Screen {
    private Stage stage;
    private final TextButton loginButton;
    private final TextButton goBackButton;
    private final TextButton forgetPasswordButton;
    private final TextField usernameField;
    private final TextField passwordField;
    private TextField securityAnswerField;
    private Label securityQuestionLabel;
    private TextField newPasswordField;
    private TextButton confirmResetButton;
    private final Label errorLabel;
    private final Label title;
    public Table table;
    private LoginMenuController controller;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.loginButton = new TextButton("Login", skin);
        this.goBackButton = new TextButton("Go back", skin);
        this.forgetPasswordButton = new TextButton("Forget pass", skin);
        this.title = new Label("L o g i n    M e n u", skin.get("title", Label.LabelStyle.class));
        this.errorLabel = new Label("", skin);
        errorLabel.setColor(1, 0, 0, 1);
        this.usernameField = new TextField("", skin);
        usernameField.setMessageText("Enter your username");
        this.passwordField = new TextField("", skin);
        passwordField.setMessageText("Enter your password");
        securityQuestionLabel = new Label("", skin);
        securityAnswerField = new TextField("", skin);
        securityAnswerField.setMessageText("Enter security answer");
        securityAnswerField.setVisible(false);
        securityQuestionLabel.setVisible(false);
        newPasswordField = new TextField("", skin);
        newPasswordField.setMessageText("Enter new password");
        newPasswordField.setVisible(false);
        confirmResetButton = new TextButton("Reset Password", skin);
        confirmResetButton.setVisible(false);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        root.top().padTop(50);

        root.add(title).colspan(3).center().padBottom(40);
        root.row();

        root.add(errorLabel).colspan(3).center().padBottom(10);
        root.row();

        root.add().width(70);
        root.add(usernameField).width(600).height(80).padBottom(20);
        root.add();
        root.row();

        root.add().width(70);
        root.add(passwordField).width(600).height(80).padBottom(30);
        root.add();
        root.row();

        root.add();
        root.add(loginButton).width(600).height(90);
        root.add();
        root.row();

        root.add();
        Table buttonRow = new Table();
        buttonRow.padTop(20);
        buttonRow.add(forgetPasswordButton).width(315).height(100).padRight(20);
        buttonRow.add(goBackButton).width(265).height(100);
        root.add(buttonRow).colspan(1);
        root.add();
        root.row();
        root.add();
        root.add(securityQuestionLabel).padTop(10);
        root.row();
        root.add();
        root.add(securityAnswerField).width(600).padTop(10);
        root.row();
        root.add();
        root.add(newPasswordField).width(600).padTop(10);
        root.row();
        root.add();
        root.add(confirmResetButton).width(600).height(100).padTop(10);
        root.row();
        root.add();


        stage.addActor(root);
    }

    @Override
    public void render(float delta) {
        MainMenuView.initialRender(stage);
        MainMenuView.setBackground(stage);
        controller.handleMainMenuButtons();
    }

    @Override
    public void resize(int width, int height) {

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

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getGoBackButton() {
        return goBackButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextField getSecurityAnswerField() {
        return securityAnswerField;
    }

    public Label getSecurityQuestionLabel() {
        return securityQuestionLabel;
    }

    public TextField getNewPasswordField() {
        return newPasswordField;
    }

    public TextButton getConfirmResetButton() {
        return confirmResetButton;
    }

    public void showSecurityQuestion(String question) {
        securityQuestionLabel.setText("Security Question: " + question);
        securityQuestionLabel.setVisible(true);
        securityAnswerField.setVisible(true);
        newPasswordField.setVisible(true);
        confirmResetButton.setVisible(true);
    }}
