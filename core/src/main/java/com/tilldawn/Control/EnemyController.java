package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Enemies.*;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;

import java.util.ArrayList;

import static com.tilldawn.Control.GameController.WIN_TIME;

public class EnemyController {
    private static ArrayList<Enemy> enemies = new ArrayList<>();
    private static ArrayList<EnemyBullet> bullets = new ArrayList<>();
    private static float spawnTentacleTimer = 0;
    private static float spawnEyeBatTimer = 0;
    private static boolean isElderAttacking = false;
    private static float damageCooldown = 1.0f;
    private static float timeSinceLastHit = 0f;

    public EnemyController() {
        // ساخت Tree Enemies در موقعیت‌های مشخص
        enemies.add(new TreeEnemy(new Vector2(300, 400)));
        enemies.add(new TreeEnemy(new Vector2(600, 800)));
        enemies.add(new TreeEnemy(new Vector2(950, 1100)));
        enemies.add(new TreeEnemy(new Vector2(1300, 500)));
        enemies.add(new TreeEnemy(new Vector2(1600, 1300)));
        enemies.add(new TreeEnemy(new Vector2(2000, 300)));
        enemies.add(new TreeEnemy(new Vector2(2300, 850)));
        enemies.add(new TreeEnemy(new Vector2(2700, 1500)));
        enemies.add(new TreeEnemy(new Vector2(3100, 600)));
        enemies.add(new TreeEnemy(new Vector2(3400, 900)));

        enemies.add(new TreeEnemy(new Vector2(400, 1900)));
        enemies.add(new TreeEnemy(new Vector2(700, 1750)));
        enemies.add(new TreeEnemy(new Vector2(1100, 2100)));
        enemies.add(new TreeEnemy(new Vector2(1500, 1950)));
        enemies.add(new TreeEnemy(new Vector2(1900, 2350)));
        enemies.add(new TreeEnemy(new Vector2(2300, 1750)));
        enemies.add(new TreeEnemy(new Vector2(2700, 2150)));
        enemies.add(new TreeEnemy(new Vector2(3100, 1950)));
        enemies.add(new TreeEnemy(new Vector2(3450, 2300)));
        enemies.add(new TreeEnemy(new Vector2(3700, 2600)));
    }

    public static void update(float delta) {
        spawnTentacleTimer -= delta;

        if (spawnTentacleTimer <= 0) {
            int count = (int)(GameController.getTotalGameTime() / 30f);
            for (int j = 0; j < count; j++) {
                spawnTentacleOnly();
            }
            spawnTentacleTimer = 3f; // هر 3 ثانیه
        }

        if (GameController.getTotalGameTime() >= WIN_TIME / 4) {
            spawnEyeBatTimer -= delta;
            if (spawnEyeBatTimer <= 0) {
                int count = (int)((GameController.getTotalGameTime() * 4f - WIN_TIME + 30) / 30);
                for (int j = 0; j < count; j++) {
                    spawnEyeBat();
                }
                spawnEyeBatTimer = 10f;
            }

        }

        if (GameController.getTotalGameTime() >= WIN_TIME / 2) {
            if (!isElderAttacking) {
                isElderAttacking = true;
                spawnElderBoss();
            }
        }
        Player player = Game.getCurrentPlayer();
        CollisionRect playerRect = player.getRect();
        playerRect.move(player.getPosX(), player.getPosY());

        // دشمن‌ها
        for (Enemy enemy : enemies) {
            enemy.update(delta);
            enemy.getRect().move(enemy.getPosition().x, enemy.getPosition().y);

            if (playerRect.collidesWith(enemy.getRect())) {
                timeSinceLastHit += delta;
                if (timeSinceLastHit >= damageCooldown) {
                    player.takeDamage(2);
                    System.out.println(player.getPlayerHealth());
                    timeSinceLastHit = 0f;
                }
            }

        }
        enemies.removeIf(Enemy::isDead);

        // تیرها
        for (EnemyBullet b : bullets) b.update(delta);
        bullets.removeIf(EnemyBullet::isDestroyed);
    }

    public static void render(SpriteBatch batch) {
        for (Enemy e : enemies) {
            e.render(batch);
        }
        for (EnemyBullet b : bullets) b.render(batch);
    }

    private static void spawnTentacleOnly() {
        Vector2 pos = getRandomEdgePosition();
        enemies.add(new TentacleMonster(pos));
    }

    private static void spawnEyeBat() {
        Vector2 pos = getRandomEdgePosition();
        enemies.add(new Eyebat(pos));
    }

    private static void spawnElderBoss() {
        Vector2 pos = getRandomEdgePosition();
        enemies.add(new ElderBoss(pos));
    }


    private static void spawnRandomEnemy() {
        Vector2 pos = getRandomEdgePosition();

        // مثلاً رندوم بین Tentacle و Eyebat
        float t = GameController.getTotalGameTime();
        if (t < 30) {
            enemies.add(new TentacleMonster(pos));
        } else if (t < 60) {
            enemies.add(Math.random() < 0.5 ? new TentacleMonster(pos) : new Eyebat(pos));
        } else {
            enemies.add(new Eyebat(pos));
        }
    }

    private static float calculateSpawnRate() {
        float t = GameController.getTotalGameTime();
        return Math.max(1, 30 - t); // تا سرعت اسپاون زیاد شه
    }

    private static Vector2 getRandomEdgePosition() {
        // لبه‌های صفحه
        int side = MathUtils.random(3);
        float x = 0, y = 0;

        switch (side) {
            case 0: x = 0; y = MathUtils.random(0, Gdx.graphics.getHeight()); break;
            case 1: x = Gdx.graphics.getWidth(); y = MathUtils.random(0, Gdx.graphics.getHeight()); break;
            case 2: x = MathUtils.random(0, Gdx.graphics.getWidth()); y = 0; break;
            case 3: x = MathUtils.random(0, Gdx.graphics.getWidth()); y = Gdx.graphics.getHeight(); break;
        }

        return new Vector2(x, y);
    }

    public static void addBullet(EnemyBullet b) {
        bullets.add(b);
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}

