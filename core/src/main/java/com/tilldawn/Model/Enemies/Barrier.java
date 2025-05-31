package com.tilldawn.Model.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Player;


public class Barrier {
    private CollisionRect rect;
    private float shrinkRate;
    private boolean active = false;
    private boolean shrinking = false;

    public Barrier(float screenWidth, float screenHeight) {
        rect = new CollisionRect(0, 0, screenWidth, screenHeight);
        shrinkRate = 8;
    }

    public void update(float delta) {
        if (shrinking) {
            rect.x += (shrinkRate * delta / 2);
            rect.y += (shrinkRate * delta / 2);
            rect.width -= (shrinkRate * delta);
            rect.height -= (shrinkRate * delta);
        }
    }

    public void render(SpriteBatch batch) {
        if (active) {
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
            shapeRenderer.end();
        }
    }

    public void activate() {
        active = true;
        shrinking = true;
    }

    public void deactivate() {
        active = false;
        shrinking = false;
    }

    public boolean isActive() {
        return active;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean collidesWith(Player player) {
        return active && player.getRect().collidesWith(rect);
    }
}

