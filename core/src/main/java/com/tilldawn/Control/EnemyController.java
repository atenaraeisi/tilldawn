package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.*;
import com.tilldawn.Model.Enemies.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.tilldawn.Control.GameController.WIN_TIME;

public class EnemyController {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<EnemyBullet> bullets = new ArrayList<>();
    private ArrayList<Seed> seeds = new ArrayList<>();
    private float spawnTentacleTimer = 0;
    private float spawnEyeBatTimer = 0;
    private boolean isElderAttacking = false;
    private float damageCooldown = 1.0f;
    private float timeSinceLastHit = 0f;

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

    public void update(float delta) {
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
                    if (Game.isSfx_enabled()) {
                        GameAssetManager.getGameAssetManager().getDamageSound().play();
                    }
                    timeSinceLastHit = 0f;
                }
            }
            if (enemy.isDead() && !enemy.hasDroppedSeed()) {
                Vector2 vector = new Vector2(enemy.getPosition().x + 5, enemy.getPosition().y + 5);
                seeds.add(new Seed(vector));
                enemy.setDroppedSeed();
            }
            if (enemy.isDead() && Game.isSfx_enabled()) {
                GameAssetManager.getGameAssetManager().getExplosionSound().play();
            }
        }
        enemies.removeIf(Enemy::isDead);

        Iterator<Seed> seedIterator = seeds.iterator();
        while (seedIterator.hasNext()) {
            Seed seed = seedIterator.next();
            if (player.getRect().collidesWith(seed.getRect())) {
                if (Game.isSfx_enabled()) GameAssetManager.getGameAssetManager().getGainPointSound().play();
                player.addXp(3);
                seedIterator.remove();
            }
        }

        // تیرها
        for (EnemyBullet b : bullets) {
            b.update(delta);
            if (b.collidesWith(player) && !b.isColliding()) {
                player.takeDamage(b.getDamage());
                b.setColliding(true);
            }
        }
        bullets.removeIf(EnemyBullet::isDestroyed);
    }

    public void render(SpriteBatch batch) {
        for (Enemy e : enemies) {
            e.render(batch);
        }
        for (EnemyBullet b : bullets) b.render(batch);
        for (Seed s : seeds) {
            s.render(batch);
        }

    }

    private void spawnTentacleOnly() {
        Vector2 pos = getRandomEdgePosition();
        enemies.add(new TentacleMonster(pos));
    }

    private void spawnEyeBat() {
        Vector2 pos = getRandomEdgePosition();
        enemies.add(new Eyebat(pos, this));
    }

    private void spawnElderBoss() {
        Vector2 pos = getRandomEdgePosition();
        enemies.add(new ElderBoss(pos));
    }


    private Vector2 getRandomEdgePosition() {
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

    public void addBullet(EnemyBullet b) {
        bullets.add(b);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<EnemyBullet> getBullets() {
        return bullets;
    }
}

