package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Model.Bullet;
import com.tilldawn.Model.Enemies.AbstractEnemy;
import com.tilldawn.Model.Enemies.Enemy;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;
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
        Game.setCurrentPlayer(new Player(Game.getCurrentUser()));
        playerController = new PlayerController(Game.getCurrentPlayer());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(new Weapon());
        enemyController = new EnemyController();
    }

    public void updateGame() {
        if (view != null) {
            totalGameTime += Gdx.graphics.getDeltaTime();
            playerController.update();
            weaponController.update();
            EnemyController.update(Gdx.graphics.getDeltaTime());
            ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
            for (Bullet bullet : weaponController.getBullets()) {
                for (Enemy enemy : EnemyController.getEnemies()) {
                    if (bullet.collidesWith(enemy)) {
                        enemy.takeDamage(bullet.getDamage());
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
