package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Control.EnemyController;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.GameAssetManager;


public class Eyebat extends AbstractEnemy {
    private float shootCooldown = 3f;

    public Eyebat(Vector2 pos) {
        super(pos, 50);
        this.enemyTexture = GameAssetManager.getGameAssetManager().getEyeBat_idle0_tex();
        this.sprite = new Sprite(enemyTexture);
        this.sprite.setSize((float) enemyTexture.getWidth() / 2 , (float) enemyTexture.getHeight() / 2);
        this.animation = GameAssetManager.getGameAssetManager().getEyeBat_idle_frames();
        this.rect = new CollisionRect(pos.x, pos.y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (hp <= 0) return;
        Vector2 playerPos = Game.getCurrentPlayer().getPosition();
        velocity = new Vector2(playerPos).sub(position).nor().scl(50 * delta);
        position.add(velocity);

        shootCooldown -= delta;
        if (shootCooldown <= 0f) {
            shootAtPlayer();
            shootCooldown = 3f;
        }
    }

    private void shootAtPlayer() {
        Vector2 dir = new Vector2(Game.getCurrentPlayer().getPosition()).sub(position).nor();
        EnemyBullet bullet = new EnemyBullet(position.cpy(), dir);
        EnemyController.addBullet(bullet);
    }
}
