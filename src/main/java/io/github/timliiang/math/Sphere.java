package io.github.timliiang.math;

import java.util.Optional;

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

    /*
     * @param r is a Ray object
     */
    public Optional<Float> intersection(Ray r) {
        Vec3 d = r.getDirection();
        Vec3 o = r.getOrigin();
        Vec3 cPrime = Vec3.sub(o, center);

        float a = Vec3.dot(d, d);
        float b = Vec3.dot(cPrime, d) * 2;
        float c = Vec3.dot(cPrime, cPrime) - radius * radius;

        float discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return Optional.empty();
        }
        float sqrtD = (float) Math.sqrt((double)discriminant);
        float t = Math.min(
                    (-b + sqrtD) / 2 * a,
                    (-b - sqrtD) / 2 * a);

        return (t > 0) ? Optional.of(t) : Optional.empty();
    }

}
