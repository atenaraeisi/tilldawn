package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.RegisterMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class RegisterMenuView implements Screen {
    private Stage stage;
    private final TextButton registerButton;
    private final TextButton goBackButton;
    private final TextField usernameField;
    private final TextField passwordField;
    private final Label errorLabel;
    private final Label title;
    private final SelectBox<String> questionSelectBox;
    private final Label questionLabel;
    TextField answerField;
    public Table table;
    private RegisterMenuController controller;

    public RegisterMenuView(RegisterMenuController controller, Skin skin) {
        this.controller = controller;
        this.registerButton = new TextButton("Register", skin);
        this.goBackButton = new TextButton("Go back", skin);
        this.title = new Label("R e g i s t e r    M e n u", skin.get("title", Label.LabelStyle.class));
        this.errorLabel = new Label("", skin);
        errorLabel.setColor(1, 0, 0, 1);
        this.usernameField = new TextField("", skin);
        usernameField.setMessageText("Enter your username");
        this.passwordField = new TextField("", skin);
        passwordField.setMessageText("Enter your password");
        this.questionSelectBox = new SelectBox<>(skin);
        questionSelectBox.setItems(
            "What is the name of your first school?",
            "What is the name of your first pet?",
            "In what city were you born?",
            "What is your favorite movie or book?",
            "What is your mother’s maiden name?"
        );
        questionLabel = new Label("Select a security question:", skin);
        answerField = new TextField("", skin);
        answerField.setMessageText("Your answer");


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

        root.add(title).colspan(3).center().padBottom(100);
        root.row();

        root.add().width(70); // ستون خالی برای مرکز کردن
        root.add(usernameField).width(500).height(100).padBottom(20);
        root.add(); // ستون خالی دیگر
        root.row();

        root.add().width(70);
        root.add(passwordField).width(500).height(100).padBottom(20);
        root.row();
        root.add();
        root.add(questionLabel).width(500).padBottom(10);
        root.row();
        root.add();
        root.add(questionSelectBox).width(500).padBottom(20);
        root.row();
        root.add();
        root.add(answerField).width(500).padBottom(20);
        root.row();
        root.add();
        root.add(errorLabel).colspan(3).center().padBottom(10);
        root.row();

        root.add(); // ستون خالی چپ
        Table buttonRow = new Table();
        buttonRow.add(registerButton).width(240).padRight(20);
        buttonRow.add(goBackButton).width(240);
        root.add(buttonRow).colspan(1).padBottom(20); // ستون وسط برای دکمه‌ها
        root.add(); // ستون خالی راست
        root.row();

        root.row();
        root.add(errorLabel).colspan(3).center().padTop(10);


        stage.addActor(root);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.05f, 0.05f, 0.1f, 1);
        if (GameAssetManager.getGameAssetManager().isBlackAndWhiteEnabled()) {
            Main.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
            stage.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
        } else {
            Main.getBatch().setShader(null);
            stage.getBatch().setShader(null);
        }
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleRegisterMenuButtons();
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

    }

    public TextButton getRegisterButton() {
        return registerButton;
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

    public SelectBox<String> getQuestionSelectBox() {
        return questionSelectBox;
    }

    public String getSelectedQuestion() {
        return questionSelectBox.getSelected();
    }

    public TextField getAnswerField() {
        return answerField;
    }
}
