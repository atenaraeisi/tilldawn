package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;

public class EnemyBullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite;
    private Vector2 position;
    private Vector2 direction;
    private float speed = 300f;
    private int damage = 10;
    private boolean destroyed = false;

    public EnemyBullet(Vector2 position, Vector2 direction) {
        this.position = new Vector2(position);
        this.direction = new Vector2(direction).nor(); // نرمال‌سازی جهت
        this.sprite = new Sprite(texture);
        sprite.setSize(15, 15);
        sprite.setPosition(position.x, position.y);
    }

    public void update(float delta) {
        Vector2 velocity = new Vector2(direction).scl(speed * delta);
        position.add(velocity);
        sprite.setPosition(position.x, position.y);

        checkCollisionWithPlayer();
    }

    private void checkCollisionWithPlayer() {
        Player player = Game.getCurrentPlayer();
        if (sprite.getBoundingRectangle().overlaps(player.getPlayerSprite().getBoundingRectangle())) {
            player.takeDamage(damage);
            destroyed = true;
        }
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}

