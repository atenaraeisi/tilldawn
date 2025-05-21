package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.EntryMenuView;
import com.tilldawn.View.SettingsMenuView;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SettingsMenuController {
    private SettingsMenuView view;
    private float lastVolume = -1f;


    private boolean lastMusicButtonState = false;
    private boolean lastSfxButtonState = false;
    private boolean lastControlsButtonState = false;
    private boolean lastAutoReloadState = false;
    private boolean lastBWState = false;
    private boolean lastBackButtonState = false;

    public void setView(SettingsMenuView view) {
        this.view = view;
    }

    public void handleSettingsMenuInputs() {
        if (view == null) return;

        // Handle Volume Slider
        Slider volumeSlider = view.getVolumeSlider();
        if (volumeSlider != null) {
            float currentVolume = volumeSlider.getValue();
            if (currentVolume != lastVolume) {
                lastVolume = currentVolume;
                GameAssetManager.getGameAssetManager().getBackgroundMusic().setVolume(currentVolume);
            }
        }

        // Handle Play/Pause Music Button
        if (view.getToggleMusicButton().isChecked() && !lastMusicButtonState) {
            toggleMusic();
            lastMusicButtonState = true;
        } else if (!view.getToggleMusicButton().isChecked()) {
            lastMusicButtonState = false;
        }

        // Handle Toggle SFX Button
        if (view.getToggleSfxButton().isChecked() && !lastSfxButtonState) {
            toggleSfx();
            lastSfxButtonState = true;
        } else if (!view.getToggleSfxButton().isChecked()) {
            lastSfxButtonState = false;
        }

        // Handle Change Controls Button
        if (view.getChangeControlsButton().isChecked() && !lastControlsButtonState) {
            changeControls();
            lastControlsButtonState = true;
        } else if (!view.getChangeControlsButton().isChecked()) {
            lastControlsButtonState = false;
        }

        // Handle Auto-Reload Checkbox
        CheckBox autoReloadCheck = view.getAutoReloadCheckBox();
        if (autoReloadCheck.isChecked() != lastAutoReloadState) {
            toggleAutoReload();
            lastAutoReloadState = autoReloadCheck.isChecked();
        }

        // Handle B&W Mode Checkbox
        CheckBox bwCheck = view.getBwModeCheckBox();
        if (bwCheck.isChecked() != lastBWState) {
            toggleBlackAndWhiteMode();
            lastBWState = bwCheck.isChecked();
        }

        // Handle Back Button
        if (view.getBackButton().isChecked() && !lastBackButtonState) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new EntryMenuView(new EntryMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            lastBackButtonState = true;
        } else if (!view.getBackButton().isChecked()) {
            lastBackButtonState = false;
        }
    }

    // Sample Methods:
    public void toggleMusic() {
        Music music = GameAssetManager.getGameAssetManager().getBackgroundMusic();
        if (music.isPlaying()) music.pause();
        else music.play();
    }

    public void toggleSfx() {
        // Toggle SFX enabled/disabled (implement based on your SFX system)
    }

    public void changeControls() {
        // Logic to open control configuration screen
    }

    public void toggleAutoReload() {
        // Implement auto-reload toggle logic
    }

    public void toggleBlackAndWhiteMode() {
        // Implement display mode toggle logic
    }
}
