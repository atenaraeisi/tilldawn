package com.tilldawn.Model;

import com.badlogic.gdx.Input;
import com.tilldawn.Control.data.UserDataSQL;

import java.util.ArrayList;

public class Game {
    private static User currentUser;
    private static Player currentPlayer;
    private static int upButton = Input.Keys.W;
    private static int downButton = Input.Keys.S;
    private static int leftButton = Input.Keys.A;
    private static int rightButton = Input.Keys.D;
    private static int reloadButton = Input.Keys.R;
    private static boolean autoReload = false;
    private static boolean autoAimEnabled = false;
    private static GameState gameState = GameState.PLAYING;
    private static boolean sfx_enabled = true;

    public static boolean isUsernameExist(String username) {
        for (User user : UserDataSQL.getInstance().getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentUser(User currentUser) {
        Game.currentUser = currentUser;
    }

    public static boolean isAutoAimEnabled() { return autoAimEnabled; }

    public static void toggleAutoAim() { autoAimEnabled = !autoAimEnabled; }

    public static int getRightButton() {
        return rightButton;
    }

    public static void setRightButton(int rightButton) {
        Game.rightButton = rightButton;
    }

    public static int getLeftButton() {
        return leftButton;
    }

    public static void setLeftButton(int leftButton) {
        Game.leftButton = leftButton;
    }

    public static int getDownButton() {
        return downButton;
    }

    public static void setReloadButton(int reloadButton) {
        Game.reloadButton = reloadButton;
    }

    public static int getReloadButton() {
        return reloadButton;
    }

    public static void setDownButton(int downButton) {
        Game.downButton = downButton;
    }

    public static int getUpButton() {
        return upButton;
    }

    public static boolean isAutoReload() {
        return autoReload;
    }

    public static void setAutoReload(boolean autoReload) {
        Game.autoReload = autoReload;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        Game.gameState = gameState;
    }

    public static void setUpButton(int upButton) {
        Game.upButton = upButton;
    }

    public static boolean isSfx_enabled() {
        return sfx_enabled;
    }

    public static void setSfx_enabled(boolean sfx_enabled) {
        Game.sfx_enabled = sfx_enabled;
    }
}
