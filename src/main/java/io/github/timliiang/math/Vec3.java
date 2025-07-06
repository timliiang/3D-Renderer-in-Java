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

    public static Vec3 add(Vec3 v1, Vec3 v2) {
        return new Vec3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vec3 sub(Vec3 v1, Vec3 v2) {
        return new Vec3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public Vec3 scale(float s) {
        return new Vec3(this.x * s, this.y * s, this.z * s);
    }

    public static Vec3 lerp(Vec3 v1, Vec3 v2, float a) {
        return add(v1.scale(1 - a), v2.scale(a));
    }

    public static float dot(Vec3 v1, Vec3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public void print() {
        System.out.println("x: " + this.x + ", y: " + this.y + ", z: " + this.z);
    }

}
