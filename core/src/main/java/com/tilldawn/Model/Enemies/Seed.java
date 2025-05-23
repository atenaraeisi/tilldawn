package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.CollisionRect;

public class Seed {
    private Vector2 position;
    private Texture texture = new Texture("Sprite/T/T_DevilDealPickup.png");
    private Sprite sprite = new Sprite(texture);
    private CollisionRect rect;

    public Seed(Vector2 position) {
        this.position = position;
        sprite.setPosition(position.x, position.y);
        this.sprite.setSize((float) texture.getWidth() / 2, (float) texture.getHeight() / 2);
        rect = new CollisionRect(position.x, position.y, sprite.getWidth(), sprite.getHeight());
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public CollisionRect getRect() {
        return rect;
    }

    public Vector2 getPosition() {
        return position;
    }
}

