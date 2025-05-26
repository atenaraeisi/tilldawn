package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;

public abstract class AbstractEnemy implements Enemy {
    protected Sprite sprite;
    protected Sprite deathSprite;
    protected int hp;
    protected Vector2 position;
    protected Vector2 velocity;
    protected boolean isEnemyIdle = true;
    protected boolean isEnemyRunning = false;
    private float time = 0;
    protected Animation<Texture> animation;
    protected Animation<Texture> deathAnimation;
    protected Texture enemyTexture;
    protected CollisionRect rect ;
    protected boolean isDying = false;
    protected float deathTimer = 0f;
    protected boolean hasDroppedSeed = false;
    protected boolean dead = false;



    public AbstractEnemy(Vector2 position, int hp) {
        this.position = position;
        this.hp = hp;
        this.deathSprite = new Sprite(GameAssetManager.getGameAssetManager().getFireExplosion_idle0_tex());
        deathSprite.setSize(deathSprite.getWidth() / 2 , deathSprite.getHeight() / 2);
        this.deathAnimation = GameAssetManager.getGameAssetManager().getFireExplosion_idle_frames();
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
        return dead;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        if (isDying) {
            Texture frame = deathAnimation.getKeyFrame(time, false);
            batch.draw(frame, position.x, position.y);
            time += Gdx.graphics.getDeltaTime();
        } else {
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
            if (isEnemyIdle) {
                idleAnimation();
            }
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

    @Override
    public void update(float delta) {
        if (hp <= 0) {
            if (!isDying) {
                isDying = true;
                time = 0; // شروع از فریم اول انیمیشن مرگ
            }

            if (time >= 0.6f) {
                dead = true;
                Game.getCurrentPlayer().addKill();
            }
        }

        // سایر منطق زنده بودن
    }

    public boolean hasDroppedSeed() {
        return hasDroppedSeed;
    }

    @Override
    public void setDroppedSeed() {
        hasDroppedSeed = true;
    }
}

