package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.CollisionRect;

public interface Enemy {
    void update(float delta);
    void render(SpriteBatch batch);
    void takeDamage(int amount);
    boolean isDead();
    Vector2 getPosition();
    CollisionRect getRect();
}

