package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public enum Character {
    SHANA(4, 4, GameAssetManager.getGameAssetManager().getCharacter1_idle_animation(), GameAssetManager.getGameAssetManager().getCharacter1_idle0_tex()),
    DIAMOND(1, 7, GameAssetManager.getGameAssetManager().getCharacter2_idle_frames(), GameAssetManager.getGameAssetManager().getCharacter2_idle0_tex()),
    SCARLET(5, 3, GameAssetManager.getGameAssetManager().getCharacter3_idle_frames(), GameAssetManager.getGameAssetManager().getCharacter3_idle0_tex()),
    LILITH(3, 5, GameAssetManager.getGameAssetManager().getCharacter4_idle_frames(), GameAssetManager.getGameAssetManager().getCharacter4_idle0_tex()),
    DASHER(10, 2, GameAssetManager.getGameAssetManager().getCharacter5_idle_frames(), GameAssetManager.getGameAssetManager().getCharacter5_idle0_tex());

    private final int speed;
    private final int hp;
    private final Animation<Texture> animation;
    private final Texture playerTexture;

    Character(int speed, int hp, Animation<Texture> animation, Texture playerTexture) {
        this.speed = speed;
        this.hp = hp;
        this.animation = animation;
        this.playerTexture = playerTexture;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }
}

