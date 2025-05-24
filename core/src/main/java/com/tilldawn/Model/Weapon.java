package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private final WeaponType type;
    private final Texture smgTexture;
    private Sprite smgSprite;
    private int ammo;
    private int damage;
    private int projectilePerShot;
    private int ammoMax;

    public Weapon(WeaponType type) {
        this.type = type;
        this.ammoMax = type.getAmmoMax() * 3;
        this.ammo = ammoMax;
        this.projectilePerShot = type.getProjectilesPerShot();
        this.damage = type.getDamage();
        smgTexture = type.getGunTexture();
        smgSprite = new Sprite(smgTexture);
        smgSprite.setX((float) Gdx.graphics.getWidth() / 2);
        smgSprite.setY((float) Gdx.graphics.getHeight() / 2);
        smgSprite.setSize(15,15);
    }

    public WeaponType getType() {
        return type;
    }

    public Sprite getSmgSprite() {
        return smgSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getProjectilePerShot() {
        return projectilePerShot;
    }

    public void increaseProjectilePerShot(int projectilePerShot) {
        this.projectilePerShot += projectilePerShot;
    }

    public int getAmmoMax() {
        return ammoMax;
    }

    public void increaseMaxAmmo(int ammo) {
        this.ammoMax += ammo;
    }
}
