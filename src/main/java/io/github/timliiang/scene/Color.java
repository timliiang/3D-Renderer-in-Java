package io.github.timliiang.scene;

/*
 * A Color class holding values for rgb in a range from 0 to 1.
 */
public class Color {
    public static final float MIN = 0;
    public static final float MAX = 1; 
    private float alpha = 1;
    private float r;
    private float g;
    private float b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color add(Color color) {
        return new Color(
            this.r + color.r,
            this.g + color.g,
            this.b + color.b
        );
    }

    public Color scale(float a) {
        return new Color(
            this.r * a,
            this.g * a,
            this.b * a
        );
    }

    public Color multiply(Color color) {
        return new Color(
            this.r * color.r,
            this.g * color.g,
            this.b * color.b 
        );
    }

    public Color clamp() {
        Color temp = new Color(
            r < MIN ? MIN : r > MAX ? MAX : r,
            g < MIN ? MIN : g > MAX ? MAX : g,
            b < MIN ? MIN : b > MAX ? MAX : b
        );
        return temp;
    }

    public int getRGB() {
        int argb = ((int)(alpha * 255) << 24) | ((int)(r * 255) << 16) | ((int)(g * 255) << 8) | (int)(b * 255);
        return argb;
    }
    
    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getR() {
        return this.r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return this.g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return this.b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void print() {
        System.out.println("r: " + r + ", g: " + g + ", b: " + b);
    }
}
