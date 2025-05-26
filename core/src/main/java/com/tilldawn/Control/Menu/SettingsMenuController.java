package com.tilldawn.Control.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.Menu.MainMenuView;
import com.tilldawn.View.Menu.SettingsMenuView;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SettingsMenuController {
    private SettingsMenuView view;
    private float lastVolume = -1f;


    private boolean lastSfxButtonState = false;
    private static boolean lastAutoReloadState = false;
    private boolean lastBWState = false;
    private boolean lastBackButtonState = false;
    private String lastMusicSelected = "music 1";

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

        // Handle choose musics
        String musicNumber = view.getMusics().getSelected();
        GameAssetManager assetManager = GameAssetManager.getGameAssetManager();

        if (!musicNumber.equals(lastMusicSelected)) {
            lastMusicSelected = musicNumber;

            switch (musicNumber) {
                case "music 1":
                    assetManager.changeMusic("musics/alex-productions-epic-cinematic-gaming-cyberpunk-reset(chosic.com).mp3", lastVolume);
                    break;
                case "music 2":
                    assetManager.changeMusic("musics/DRIVE(chosic.com).mp3", lastVolume);
                    break;
                case "music 3":
                    assetManager.changeMusic("musics/Sneaky-Snitch(chosic.com).mp3", lastVolume);
                    break;
                case "music 4":
                    assetManager.changeMusic("musics/Warfare-chosic.com_.mp3", lastVolume);
                    break;
                case "atena":
                    assetManager.changeMusic("musics/Salam Hello Marhaba.mp3", lastVolume);
                    break;
            }
        }


        // Handle Toggle SFX Button
        if (view.getToggleSfxButton().isChecked() && !lastSfxButtonState) {
            GameAssetManager.getGameAssetManager().getClickSound().play();
            toggleSfx();
            lastSfxButtonState = true;
        } else if (!view.getToggleSfxButton().isChecked()) {
            lastSfxButtonState = false;
        }

        // Handle Change Controls Button
        String selectedUp = view.getUpButtons().getSelected();
        String selectedDown = view.getDownButtons().getSelected();
        String selectedLeft = view.getLeftButtons().getSelected();
        String selectedRight = view.getRightButtons().getSelected();
        String selectedReload = view.getReloadButtons().getSelected();

        int upKeyCode = getKeyCodeFromString(selectedUp);
        int downKeyCode = getKeyCodeFromString(selectedDown);
        int leftKeyCode = getKeyCodeFromString(selectedLeft);
        int rightKeyCode = getKeyCodeFromString(selectedRight);
        int reloadKeyCode = getKeyCodeFromString(selectedReload);

        if (upKeyCode != -1) Game.setUpButton(upKeyCode);
        if (downKeyCode != -1) Game.setDownButton(downKeyCode);
        if (leftKeyCode != -1) Game.setLeftButton(leftKeyCode);
        if (rightKeyCode != -1) Game.setRightButton(rightKeyCode);
        if (reloadKeyCode != -1) Game.setReloadButton(reloadKeyCode);

        // Handle Auto-Reload Checkbox
        CheckBox autoReloadCheck = view.getAutoReloadCheckBox();
        if (autoReloadCheck.isChecked() != lastAutoReloadState) {
            GameAssetManager.getGameAssetManager().getClickSound().play();
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
            GameAssetManager.getGameAssetManager().getClickSound().play();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            lastBackButtonState = true;
        } else if (!view.getBackButton().isChecked()) {
            lastBackButtonState = false;
        }

    }

    public void toggleSfx() {
        Game.setSfx_enabled(!Game.isSfx_enabled());
    }

    public void toggleAutoReload() {
        Game.setAutoReload(!Game.isAutoReload());
    }

    public void toggleBlackAndWhiteMode() {
        GameAssetManager.getGameAssetManager().toggleBlackAndWhite();
    }

    private int getKeyCodeFromString(String keyName) {
        switch (keyName) {
            case "W": return com.badlogic.gdx.Input.Keys.W;
            case "A": return com.badlogic.gdx.Input.Keys.A;
            case "S": return com.badlogic.gdx.Input.Keys.S;
            case "D": return com.badlogic.gdx.Input.Keys.D;
            case "Up arrow": return com.badlogic.gdx.Input.Keys.UP;
            case "Down arrow": return com.badlogic.gdx.Input.Keys.DOWN;
            case "Left arrow": return com.badlogic.gdx.Input.Keys.LEFT;
            case "Right arrow": return com.badlogic.gdx.Input.Keys.RIGHT;
            case "R" : return com.badlogic.gdx.Input.Keys.R;
            case "Space": return com.badlogic.gdx.Input.Keys.SPACE;
            default: return -1; // خطای ناشناس
        }
    }

}
