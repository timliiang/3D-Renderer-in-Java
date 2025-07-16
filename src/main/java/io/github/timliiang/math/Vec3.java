package io.github.timliiang.math;

/*
 * This is a simple 3D vector class.
 * 
 */
public class Vec3 {
    public float x, y, z;

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vec3 sub(Vec3 v) {
        return new Vec3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vec3 scale(float s) {
        return new Vec3(this.x * s, this.y * s, this.z * s);
    }

    public static Vec3 lerp(Vec3 v1, Vec3 v2, float a) {
        return v1.scale(1 - a).add(v2.scale(a));
    }

    public float dot(Vec3 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vec3 normalize() {
        return this.scale(1 / (float) Math.sqrt(this.dot(this)));
    }

    public void print() {
        System.out.println("x: " + this.x + ", y: " + this.y + ", z: " + this.z);
    }

}
