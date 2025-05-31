package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.GameAssetManager;

public class TreeEnemy extends AbstractEnemy {
    public TreeEnemy(Vector2 pos) {
        super(pos, 99999);
        this.enemyTexture = GameAssetManager.getGameAssetManager().getTreeEnemy_idle0_tex();
        this.sprite = new Sprite(enemyTexture);
        this.animation = GameAssetManager.getGameAssetManager().getTreeEnemy_idle_frames();
        this.sprite.setSize((float) enemyTexture.getWidth() / 2 , (float) enemyTexture.getHeight() / 2);
        this.rect = new CollisionRect(pos.x, pos.y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update(float delta) {
    }
}

