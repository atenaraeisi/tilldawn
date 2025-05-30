package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;


public class ElderBoss extends AbstractEnemy {
    private float dashCooldown = 5f;
    private boolean hasShield = true;
    private float shieldShrinkRate = 1f;

    public ElderBoss(Vector2 pos) {
        super(pos, 400);
        this.enemyTexture = GameAssetManager.getGameAssetManager().getElderBoss_idle0_tex();
        this.sprite = new Sprite(enemyTexture);
        this.sprite.setSize((float) enemyTexture.getWidth() * 4 , (float) enemyTexture.getHeight() * 4);
        deathSprite.setSize((float) enemyTexture.getWidth() * 4 , (float) enemyTexture.getHeight() * 4);
        this.animation = GameAssetManager.getGameAssetManager().getElderBoss_idle_frames();
        this.rect = new CollisionRect(pos.x, pos.y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (hp <= 0) return;
        Vector2 playerPos = Game.getCurrentPlayer().getPosition();
        velocity = new Vector2(playerPos).sub(position).nor().scl(30 * delta);
        position.add(velocity);

        if (hasShield) {
            shrinkShield(delta);
        }
    }

    private void dashToPlayer() {
        Vector2 playerPos = Game.getCurrentPlayer().getPosition();
        velocity = new Vector2(playerPos).sub(position).nor().scl(500);
        position.add(velocity);
    }

    private void shrinkShield(float delta) {

    }
}

