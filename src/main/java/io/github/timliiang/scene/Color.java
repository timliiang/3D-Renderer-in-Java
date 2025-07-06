package io.github.timliiang.scene;

/*
 * A Color class holding values for rgb in a range from 0 to 1.
 */
public class Color {
    private float alpha = 1;
    private float r;
    private float g;
    private float b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getR() {
        return this.r;
    }

    public float getG() {
        return this.g;
    }

    public float getB() {
        return this.b;
    }

    public int getRGB() {
        int argb = ((int)(alpha * 255) << 24) | ((int)(r * 255) << 16) | ((int)(g * 255) << 8) | (int)(b * 255);
        return argb;
    }
}
