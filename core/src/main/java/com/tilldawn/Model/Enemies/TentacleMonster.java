package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;


public class TentacleMonster extends AbstractEnemy {

    public TentacleMonster(Vector2 pos) {
        super(pos, 25);
        this.enemyTexture = GameAssetManager.getGameAssetManager().getTentacleEnemy_idle0_tex();
        this.sprite = new Sprite(enemyTexture);
        this.sprite.setSize((float) enemyTexture.getWidth() / 2 , (float) enemyTexture.getHeight() / 2);
        this.animation = GameAssetManager.getGameAssetManager().getTentacleEnemy_idle_frames();
        this.rect = new CollisionRect(pos.x, pos.y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (hp <= 0) return;
        Vector2 playerPos = Game.getCurrentPlayer().getPosition();
        velocity = new Vector2(playerPos).sub(position).nor().scl(30 * delta);
        position.add(velocity);
    }
}
