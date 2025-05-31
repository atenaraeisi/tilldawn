package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class MainMenuView implements Screen {
    private Stage stage;
    private final TextButton settingsButton;
    private final TextButton exitButton;
    private final TextButton profileButton;
    private final TextButton scoreboardButton;
    private final TextButton pregameButton;
    private final TextButton hintsButton;

    private final Label gameTitle;
    private final TextField field;
    public Table table;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.settingsButton = new TextButton("Settings", skin);
        this.exitButton = new TextButton("Home Page", skin);
        this.profileButton = new TextButton("Profile Menu", skin);
        this.scoreboardButton = new TextButton("Scoreboard", skin);
        this.pregameButton = new TextButton("Pre-game", skin);
        this.hintsButton = new TextButton("Hints", skin);
        this.gameTitle = new Label("M a i n    M e n u", skin.get("title", Label.LabelStyle.class));
        this.field = new TextField("this is a field", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        root.top().padTop(30);

        root.add(gameTitle).colspan(3).center().padBottom(100);
        root.row();

        TextButton[] buttons = {settingsButton, profileButton, pregameButton, hintsButton, scoreboardButton, exitButton};
        for (int i = 0; i < 6; i++) {
            root.add(buttons[i]).width(400).height(150).pad(30);
            if (i % 2 == 1) root.row();
        }

        stage.addActor(root);
    }

    @Override
    public void render(float delta) {
        initialRender(stage);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenuButtons();
    }

    public static void initialRender(Stage stage) {
        ScreenUtils.clear(0.05f, 0.05f, 0.1f, 1);
        if (GameAssetManager.getGameAssetManager().isBlackAndWhiteEnabled()) {
            Main.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
            stage.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
        } else {
            Main.getBatch().setShader(null);
            stage.getBatch().setShader(null);
        }
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

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public TextButton getScoreboardButton() {
        return scoreboardButton;
    }

    public TextButton getHintsButton() {
        return hintsButton;
    }

    public TextButton getPregameButton() {
        return pregameButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextField getField() {
        return field;
    }
}
