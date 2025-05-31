package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.Enemies.Enemy;

public class Bullet {
    private Texture texture;
    private Animation<Texture> animation;
    private CollisionRect rect;
    private Sprite sprite;
    private int x;
    private int y;
    private Vector2 direction;


    public Bullet(int x, int y, Animation<Texture> animation, Texture texture) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
        sprite.setSize(10 , 10);
        this.x = x;
        this.y = y;
        Player player = Game.getCurrentPlayer();
        sprite.setX(player.getPlayerSprite().getX());
        sprite.setY(player.getPlayerSprite().getY());
        this.rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        this.animation = animation;
        this.direction = new Vector2();
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean collidesWith(Enemy enemy) {
        return rect.collidesWith(enemy.getRect());
    }


    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
