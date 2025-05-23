package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.GameAssetManager;

public abstract class AbstractEnemy implements Enemy {
    protected Sprite sprite;
    protected int hp;
    protected Vector2 position;
    protected Vector2 velocity;
    protected boolean isEnemyIdle = true;
    protected boolean isEnemyRunning = false;
    private float time = 0;
    protected Animation<Texture> animation;
    protected Texture enemyTexture;
    protected CollisionRect rect ;


    public AbstractEnemy(Vector2 position, int hp) {
        this.position = position;
        this.hp = hp;
    }

    public boolean isEnemyIdle() {
        return isEnemyIdle;
    }

    public void setEnemyIdle(boolean enemyIdle) {
        isEnemyIdle = enemyIdle;
    }

    public boolean isEnemyRunning() {
        return isEnemyRunning;
    }

    public void setEnemyRunning(boolean enemyRunning) {
        isEnemyRunning = enemyRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public CollisionRect getRect() {
        return rect;
    }

    @Override
    public void takeDamage(int amount) {
        hp -= amount;
    }

    @Override
    public boolean isDead() {
        return hp <= 0;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        if (isEnemyIdle) {
            idleAnimation();
        }
    }
    public void idleAnimation(){
        sprite.setRegion(animation.getKeyFrame(time));

        if (!animation.isAnimationFinished(time)) {
            setTime(time + Gdx.graphics.getDeltaTime());
        }
        else {
            setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
}

