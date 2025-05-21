package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Control.SettingsMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class SettingsMenuView implements Screen {
    private Stage stage;
    private final Skin skin;
    private final SettingsMenuController controller;

    private Table table;

    // Components
    private Slider volumeSlider;
    private Label changeMusic;
    private SelectBox<String> musics;
    private TextButton toggleSfxButton;
    private Label changeControls;
    private Label upButton;
    private SelectBox<String> upButtons;
    private Label downButton;
    private SelectBox<String> downButtons;
    private Label leftButton;
    private SelectBox<String> leftButtons;
    private Label rightButton;
    private SelectBox<String> rightButtons;
    private CheckBox autoReloadCheckBox;
    private CheckBox bwModeCheckBox;
    private TextButton backButton;
    private Label title;
    private Label volumeLabel;

    public SettingsMenuView(SettingsMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);
        setupUI();
    }

    private void setupUI() {
        table = new Table();
        table.setFillParent(true);

        // Title Label
        title = new Label("Settings", skin.get("title", Label.LabelStyle.class));

        // Volume Slider
        volumeLabel = new Label("Volume", skin);
        volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(GameAssetManager.getGameAssetManager().getBackgroundMusic().getVolume()); // Default

        // Music Toggle
        changeMusic = new Label("Music", skin);
        musics = new SelectBox<>(skin);
        musics.setItems(
            "music 1",
            "music 2",
            "music 3",
            "music 4",
            "music 5"
        );

        // SFX Toggle
        toggleSfxButton = new TextButton("Toggle SFX", skin);
        toggleSfxButton.setChecked(false);

        // Change Controls
        changeControls = new Label("Controls buttons:", skin);
        upButton = new Label("Up:", skin);
        downButton = new Label("Down:", skin);
        leftButton = new Label("Left:", skin);
        rightButton = new Label("Right:", skin);
        upButtons = new SelectBox<>(skin);
        upButtons.setItems(
            "W",
            "Up arrow"
        );
        downButtons = new SelectBox<>(skin);
        downButtons.setItems(
            "S",
            "Down arrow"
            );
        leftButtons = new SelectBox<>(skin);
        leftButtons.setItems(
            "A",
            "Left arrow"
        );
        rightButtons = new SelectBox<>(skin);
        rightButtons.setItems(
            "D",
            "Right arrow"
        );

        // Auto Reload Checkbox
        autoReloadCheckBox = new CheckBox("Auto Reload", skin);

        // Black & White Mode Checkbox
        bwModeCheckBox = new CheckBox("B&W Mode", skin);

        // Back Button
        backButton = new TextButton("Back", skin);
        backButton.setChecked(false);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // Layout
        table.add(title).colspan(3).center().padBottom(100);
        table.row();

        table.add();
        Table musicRow = new Table();
        musicRow.add(changeMusic).width(100).height(90);
        musicRow.add(musics).width(300).padLeft(20);
        musicRow.add(volumeLabel).padLeft(50);
        musicRow.add(volumeSlider).width(300).padLeft(20);
        musicRow.add(toggleSfxButton).width(300).padLeft(50);
        table.add(musicRow).colspan(1).padBottom(30);
        table.add();
        table.row();
        table.add();
        Table controlsRow = new Table();
        controlsRow.add(changeControls).padBottom(20).row();
        controlsRow.add(upButton);
        controlsRow.add(upButtons).width(150).padRight(40);
        controlsRow.add(downButton).padRight(10);
        controlsRow.add(downButtons).width(150).padRight(40);
        controlsRow.add(leftButton).padRight(10);
        controlsRow.add(leftButtons).width(150).padRight(40);
        controlsRow.add(rightButton).padRight(10);
        controlsRow.add(rightButtons).width(150).padRight(40);
        table.add(controlsRow).colspan(1).padBottom(50);
        table.add();
        table.row();
        table.add();
        table.add(autoReloadCheckBox).pad(10).row();
        table.add();
        table.add(bwModeCheckBox).pad(10).row();
        table.add();
        table.add(backButton).padTop(30).width(150);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.05f, 0.05f, 0.1f, 1); // آبی تیره جذاب
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleSettingsMenuInputs(); // Controller logic called here
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public SelectBox<String> getMusics() {
        return musics;
    }

    public TextButton getToggleSfxButton() {
        return toggleSfxButton;
    }

    public CheckBox getAutoReloadCheckBox() {
        return autoReloadCheckBox;
    }

    public CheckBox getBwModeCheckBox() {
        return bwModeCheckBox;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public SelectBox<String> getDownButtons() {
        return downButtons;
    }

    public SelectBox<String> getLeftButtons() {
        return leftButtons;
    }

    public SelectBox<String> getRightButtons() {
        return rightButtons;
    }

    public SelectBox<String> getUpButtons() {
        return upButtons;
    }
}
