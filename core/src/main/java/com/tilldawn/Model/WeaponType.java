package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;

public enum WeaponType {
    REVOLVER(6, 1, 1, 20, GameAssetManager.getGameAssetManager().getSmgTexture()),
    SHOTGUN(2, 1, 4, 10, GameAssetManager.getGameAssetManager().getSmgDualTexture()),
    SMGS_DUAL(24, 2, 1, 8, GameAssetManager.getGameAssetManager().getShotGunTexture()),;

    private final int ammoMax;
    private final int reloadTimeSeconds;
    private final int projectilesPerShot;
    private final int damage;
    private final Texture gunTexture;

    WeaponType(int ammoMax, int reloadTimeSeconds, int projectilesPerShot, int damage, Texture texture) {
        this.ammoMax = ammoMax;
        this.reloadTimeSeconds = reloadTimeSeconds;
        this.projectilesPerShot = projectilesPerShot;
        this.damage = damage;
        this.gunTexture = texture;
    }

    public Texture getGunTexture() {
        return gunTexture;
    }

    public int getAmmoMax() {
        return ammoMax;
    }

    public int getReloadTimeSeconds() {
        return reloadTimeSeconds;
    }

    public int getProjectilesPerShot() {
        return projectilesPerShot;
    }

    public int getDamage() {
        return damage;
    }
}
