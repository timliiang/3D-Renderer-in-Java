package io.github.timliiang.math;

public class Ray {
    private Vec3 origin; 
    private Vec3 direction;

    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vec3 getOrigin() {
        return this.origin;
    }

    public Vec3 getDirection() {
        return this.direction;
    }

}
