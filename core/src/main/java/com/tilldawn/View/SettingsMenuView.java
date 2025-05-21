package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Control.SettingsMenuController;

public class SettingsMenuView implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final SettingsMenuController controller;

    private Table table;

    // Components
    private Slider volumeSlider;
    private TextButton toggleMusicButton;
    private TextButton toggleSfxButton;
    private TextButton changeControlsButton;
    private CheckBox autoReloadCheckBox;
    private CheckBox bwModeCheckBox;
    private TextButton backButton;

    public SettingsMenuView(SettingsMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        controller.setView(this);
        setupUI();
    }

    private void setupUI() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Title Label
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label title = new Label("Settings", labelStyle);
        title.setFontScale(2f);

        // Volume Slider
        Label volumeLabel = new Label("Volume", skin);
        volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(0.5f); // Default

        // Music Toggle
        toggleMusicButton = new TextButton("Toggle Music", skin);
        toggleMusicButton.setChecked(false);

        // SFX Toggle
        toggleSfxButton = new TextButton("Toggle SFX", skin);
        toggleSfxButton.setChecked(false);

        // Change Controls
        changeControlsButton = new TextButton("Change Controls", skin);
        changeControlsButton.setChecked(false);

        // Auto Reload Checkbox
        autoReloadCheckBox = new CheckBox("Auto Reload", skin);

        // Black & White Mode Checkbox
        bwModeCheckBox = new CheckBox("B&W Mode", skin);

        // Back Button
        backButton = new TextButton("Back", skin);
        backButton.setChecked(false);

        // Layout
        table.add(title).padBottom(40).colspan(2).center().row();
        table.add(volumeLabel).left().pad(10);
        table.add(volumeSlider).width(200).pad(10).row();
        table.add(toggleMusicButton).colspan(2).pad(10).fillX().row();
        table.add(toggleSfxButton).colspan(2).pad(10).fillX().row();
        table.add(changeControlsButton).colspan(2).pad(10).fillX().row();
        table.add(autoReloadCheckBox).colspan(2).pad(10).left().row();
        table.add(bwModeCheckBox).colspan(2).pad(10).left().row();
        table.add(backButton).colspan(2).padTop(30).center().width(150);
    }

    // Getters for controller
    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public TextButton getToggleMusicButton() {
        return toggleMusicButton;
    }

    public TextButton getToggleSfxButton() {
        return toggleSfxButton;
    }

    public TextButton getChangeControlsButton() {
        return changeControlsButton;
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
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
}
