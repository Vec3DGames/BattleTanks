package xyz.vec3d.game.model;

/**
 * Created by Daron on 11/5/2017.
 * Copyright vec3d.xyz 2016
 * All rights reserved
 */

public abstract class Entity {

    private float x;
    private float y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract void render(float delta);

    public abstract void update(float delta);
}
