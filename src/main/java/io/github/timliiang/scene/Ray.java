package io.github.timliiang.scene;

import io.github.timliiang.math.Vec3;

public class Ray {
    public Vec3 origin, direction;

    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

}
