package io.github.timliiang.scene;

import io.github.timliiang.math.Vec3;

public class ImagePlane {
    private Vec3 x1;
    private Vec3 x2;
    private Vec3 x3;
    private Vec3 x4;

    public ImagePlane(Vec3 x1, Vec3 x2, Vec3 x3, Vec3 x4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
    }
    
    public Vec3 getX1() {
        return this.x1;
    }

    public Vec3 getX2() {
        return this.x2;
    }

    public Vec3 getX3() {
        return this.x3;
    }

    public Vec3 getX4() {
        return this.x4;
    }

}
