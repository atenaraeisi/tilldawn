package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Control.EnemyController;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;

public class EnemyBullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Texture collisionTexture =GameAssetManager.getGameAssetManager().getFlash_idle0_tex();
    private Animation<Texture> destrooyedAnimation;
    private Sprite sprite;
    private Vector2 position;
    private CollisionRect rect;
    private Vector2 direction;
    private float speed = 200f;
    private int damage = 1;
    private boolean destroyed = false;
    private boolean isColliding = false;
    private float time = 0;

    public EnemyBullet(Vector2 position, Vector2 direction) {
        this.position = new Vector2(position);
        this.direction = new Vector2(direction).nor(); // نرمال‌سازی جهت
        this.sprite = new Sprite(texture);
        sprite.setSize(15, 15);
        sprite.setPosition(position.x, position.y);
        this.rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        this.destrooyedAnimation = GameAssetManager.getGameAssetManager().getFlash_idle_frames();
    }

    public void update(float delta) {
        if (isColliding) {
            if (time >= 0.6f) {
                destroyed = true;
            }
            return;
        }
        Vector2 velocity = new Vector2(direction).scl(speed * delta);
        position.add(velocity);
        sprite.setPosition(position.x, position.y);
        rect.move(sprite.getX(), sprite.getY());

    }

    public boolean collidesWith(Player player) {
        return rect.collidesWith(player.getRect());
    }

    public void render(SpriteBatch batch) {
        if (isColliding) {
            Texture frame = destrooyedAnimation.getKeyFrame(time, false);
            batch.draw(frame, position.x, position.y);
            time += Gdx.graphics.getDeltaTime();
        } else sprite.draw(batch);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setColliding(boolean colliding) {
        isColliding = colliding;
        time = 0;
    }

    public boolean isColliding() {
        return isColliding;
    }
}

