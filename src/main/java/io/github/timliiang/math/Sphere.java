package io.github.timliiang.math;

import io.github.timliiang.scene.Color;

public class Sphere {
    private Vec3 center;
    private float radius;
    private Color color;

    public Sphere(Vec3 center, float radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Vec3 getCenter() {
        return this.center;
    }

    public float getRadius() {
        return this.radius;
    }

    public Color getColor() {
        return this.color;
    }

}
