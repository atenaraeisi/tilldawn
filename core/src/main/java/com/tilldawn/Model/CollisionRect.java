package com.tilldawn.Model;

public class CollisionRect {
    public float x, y;
    public float width, height;
    public CollisionRect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(CollisionRect rect){
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
