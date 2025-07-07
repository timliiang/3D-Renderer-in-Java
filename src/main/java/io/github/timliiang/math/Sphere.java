package io.github.timliiang.math;

import java.util.Optional;

import io.github.timliiang.scene.Material;
import io.github.timliiang.scene.SceneObject;

public class Sphere implements SceneObject {
    private Vec3 center;
    private float radius;
    private Material material;

    public Sphere(Vec3 center, float radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public Vec3 getCenter() {
        return this.center;
    }

    public float getRadius() {
        return this.radius;
    }

    public Material getMaterial() {
        return this.material;
    }

    /*
     * @param r is a Ray object
     */
    @Override
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
