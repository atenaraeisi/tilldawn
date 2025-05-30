package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.GameView;

import java.util.ArrayList;

public class WeaponController {
    private Weapon weapon;
    private boolean isReloading = false;
    private float reloadTimer = 0f;
    private boolean isDamageIncreased = false;
    private float increaseTimer = 0f;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public WeaponController(Weapon weapon){
        this.weapon = weapon;
    }


    public void update(){
        Player player = Game.getCurrentPlayer();
        weapon.getSmgSprite().draw(Main.getBatch());
        weapon.getSmgSprite().setPosition(player.getPosX() + (player.getPlayerSprite().getWidth() / 2), player.getPosY() + player.getPlayerSprite().getHeight() / 6);

        if (isDamageIncreased) {
            increaseTimer -= Gdx.graphics.getDeltaTime();
            if (increaseTimer <= 0) {
                isDamageIncreased = false;
                weapon.decreaseDamage();
            }
        }
        if (isReloading) {
            reloadTimer -= Gdx.graphics.getDeltaTime();
            if (reloadTimer <= 0) {
                isReloading = false;
                weapon.setAmmo(weapon.getAmmoMax());
            }
        }
        if (!isReloading && weapon.getAmmo() == 0 && Game.isAutoReload()) {
            startReload();
        }
        updateBullets();
    }

    public void startIncrease() {
        if (isDamageIncreased) return;
        isDamageIncreased = true;
        increaseTimer = 10f;
    }

    public void startReload() {
        if (isReloading) return;
        isReloading = true;
        reloadTimer = weapon.getType().getReloadTimeSeconds();
    }


    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getSmgSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y) {
        if (isReloading) return;
        if (weapon.getAmmo() <= 0){
            if (Game.isAutoReload()){
                weapon.setAmmo(weapon.getAmmoMax());
            } else return;
        }

        Player player = Game.getCurrentPlayer();
        float playerX = player.getPosX() + player.getPlayerSprite().getWidth() / 2f;
        float playerY = player.getPosY() + player.getPlayerSprite().getHeight() / 2f;
        Vector2 origin = new Vector2(playerX, playerY);


        Vector3 worldClick = GameView.camera.unproject(new Vector3(x, y, 0));
        Vector2 target = new Vector2(worldClick.x, worldClick.y);

        WeaponType type = weapon.getType();

        if (weapon.getProjectilePerShot() > 1) {
            int pelletCount = weapon.getProjectilePerShot();
            float[] angleOffsets = { -5f, 5f, -10f, 10f, 15f, -15f};

            for (int i = 0; i < Math.min(pelletCount, 6); i++) {
                Bullet bullet = new Bullet(x, y,
                    GameAssetManager.getGameAssetManager().getSmgReload_idle_frames(),
                    GameAssetManager.getGameAssetManager().getSmgReload_idle0_tex());

                Vector2 direction = new Vector2(target.x - origin.x, target.y - origin.y).nor();
                direction.rotateDeg(angleOffsets[i]);

                bullet.getSprite().setPosition(origin.x, origin.y);
                bullet.getRect().move(origin.x, origin.y);
                bullet.setDirection(direction);

                bullets.add(bullet);
                if (Game.isSfx_enabled()) {
                    if (weapon.getType().equals(WeaponType.SHOTGUN)) GameAssetManager.getGameAssetManager().getShotGunSound().play();
                    else GameAssetManager.getGameAssetManager().getShootSound().play();
                }
            }
            weapon.setAmmo(weapon.getAmmo() - 1);
        } else {
            Bullet bullet = new Bullet(x, y,
                GameAssetManager.getGameAssetManager().getSmgReload_idle_frames(),
                GameAssetManager.getGameAssetManager().getSmgReload_idle0_tex());

            Vector2 direction = new Vector2(target.x - origin.x, target.y - origin.y).nor();

            bullet.getSprite().setPosition(origin.x, origin.y);
            bullet.getRect().move(origin.x, origin.y);
            bullet.setDirection(direction);

            bullets.add(bullet);
            if (Game.isSfx_enabled()) {
                if (weapon.getType().equals(WeaponType.SHOTGUN)) GameAssetManager.getGameAssetManager().getShotGunSound().play();
                else GameAssetManager.getGameAssetManager().getShootSound().play();
            }
            weapon.setAmmo(weapon.getAmmo() - 1);
        }
    }


    public void updateBullets() {
        for (Bullet b : bullets) {
            b.getSprite().draw(Main.getBatch());

            Vector2 direction = b.getDirection();
            b.getSprite().setX(b.getSprite().getX() + direction.x * 5);
            b.getSprite().setY(b.getSprite().getY() + direction.y * 5);
            b.getRect().move(b.getSprite().getX(), b.getSprite().getY());
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
