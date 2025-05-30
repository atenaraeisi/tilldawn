package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.Enemies.Enemy;
import com.tilldawn.View.GameOverPage;
import com.tilldawn.View.GameView;
import com.tilldawn.View.WinningPage;

import java.util.ArrayList;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private EnemyController enemyController;
    public static float WIN_TIME;
    private static float totalGameTime = 0f;



    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Game.getCurrentPlayer());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(Game.getCurrentPlayer().getEquippedWeapon(), this);
        enemyController = new EnemyController();
    }

    public void updateGame() {
        if (Game.getGameState().equals(GameState.WINNING)) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new WinningPage(new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
        }
        else if (Game.getGameState().equals(GameState.GAME_OVER)) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameOverPage( new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
        }
        if (view != null && Game.getGameState() == GameState.PLAYING) {
            totalGameTime += Gdx.graphics.getDeltaTime();
            playerController.update();
            weaponController.update();
            enemyController.update(Gdx.graphics.getDeltaTime());
            ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
            for (Bullet bullet : weaponController.getBullets()) {
                for (Enemy enemy : enemyController.getEnemies()) {
                    if (bullet.collidesWith(enemy)) {
                        enemy.takeDamage(Game.getCurrentPlayer().getEquippedWeapon().getDamage());
                        bulletsToRemove.add(bullet);
                        break;
                    }
                }
            }
            weaponController.getBullets().removeAll(bulletsToRemove);
            if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSLASH)) {
                WIN_TIME--;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                if (Game.isSfx_enabled()) GameAssetManager.getGameAssetManager().getLevelUpSound().play();
                Game.getCurrentPlayer().addLevel(1);
                Game.getCurrentPlayer().setXp(0);
                Game.setGameState(GameState.PAUSED);
                view.showAbilitySelectionDialog();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
                if (Game.getCurrentPlayer().getPlayerHealth() != 0) Game.getCurrentPlayer().setPlayerHealth(10);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
                Game.getCurrentPlayer().getEquippedWeapon().increaseProjectilePerShot(1);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                Game.setGameState(GameState.BOSS_FIGHT);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                Game.toggleAutoAim();
            }

        }
    }

    public static void setWinTime(float winTime) {
        WIN_TIME = winTime;
    }

    public static void setTotalGameTime(float totalGameTime) {
        GameController.totalGameTime = totalGameTime;
    }

    public static float getTotalGameTime() {
        return totalGameTime;
    }



    public WorldController getWorldController() {
        return worldController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }
}
