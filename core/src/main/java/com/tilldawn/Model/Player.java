package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player {
    private User user;
    private Character character;
    private Weapon equippedWeapon;
    private Texture playerTexture;
    private Sprite playerSprite;
    private Animation<Texture> animation;
    private float posX;
    private float posY;
    private float playerHealth;
    private CollisionRect rect ;
    private float time = 0;
    private float speed;
    private int killCount = 0;
    private int xp = 0;
    private int level = 1;
    private int maxHp;

    public float getSpeed() {
        return speed;
    }

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(User user, Character character) {
        this.user = user;
        this.character = character;
        this.animation = character.getAnimation();
        this.playerTexture = character.getPlayerTexture();
        this.speed = character.getSpeed();
        this.maxHp = character.getHp() * 20;
        this.playerHealth = character.getHp() * 20;
        playerSprite = new Sprite(playerTexture);
        posX = Gdx.graphics.getWidth() / 2;
        posY = Gdx.graphics.getHeight() / 2;
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getWidth(), playerTexture.getHeight());
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2, playerTexture.getWidth() , playerTexture.getHeight() );
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }


    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Vector2 getPosition() {
        return new Vector2(posX, posY);
    }

    public void takeDamage(int damage) {
        playerHealth -= damage;
    }

    public User getUser() {
        return user;
    }

    public void addKill() {
        killCount++;
    }

    public int getKillCount() {
        return killCount;
    }
    public int getXp() {
        return xp;
    }

    public void addXp(int xp) {
        this.xp += xp;
    }

    public int getLevel() {
        return level;
    }

    public void addLevel(int level) {
        this.level += level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXPForNextLevel() {
        return level * 2;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Character getCharacter() {
        return character;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void increaseMaxHp(int increase) {
        maxHp += increase;
    }

    public void activateSpeedBoost(float speed) {
        this.speed = speed;
    }
}
