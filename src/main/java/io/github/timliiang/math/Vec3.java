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
        return new Vec3(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z);
    }

    public Vec3 scale(float s) {
        return new Vec3(this.x * s, this.y * s, this.z * s);
    }

    public void print() {
        System.out.println("x: " + this.x + ", y: " + this.y + ", z: " + this.z);
    }

}
