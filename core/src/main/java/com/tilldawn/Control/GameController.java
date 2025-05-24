package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Model.*;
import com.tilldawn.Model.Character;
import com.tilldawn.Model.Enemies.AbstractEnemy;
import com.tilldawn.Model.Enemies.Enemy;
import com.tilldawn.View.GameView;

import java.util.ArrayList;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private EnemyController enemyController;
    public static final float WIN_TIME = 20f; //TODO
    private static float totalGameTime = 0f;



    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Game.getCurrentPlayer());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(Game.getCurrentPlayer().getEquippedWeapon());
        enemyController = new EnemyController();
    }

    public void updateGame() {
        if (view != null && Game.getGameState() != GameState.PAUSED) {
            totalGameTime += Gdx.graphics.getDeltaTime();
            playerController.update();
            weaponController.update();
            EnemyController.update(Gdx.graphics.getDeltaTime());
            ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
            for (Bullet bullet : weaponController.getBullets()) {
                for (Enemy enemy : EnemyController.getEnemies()) {
                    if (bullet.collidesWith(enemy)) {
                        enemy.takeDamage(Game.getCurrentPlayer().getEquippedWeapon().getDamage());
                        // می‌تونی گلوله رو حذف کنی
                        bulletsToRemove.add(bullet);
                        break; // چون این گلوله فقط به یه دشمن برخورد می‌کنه
                    }
                }
            }
            weaponController.getBullets().removeAll(bulletsToRemove);

        }
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
}
