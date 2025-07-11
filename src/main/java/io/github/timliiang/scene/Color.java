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

    public static Color add(Color color1, Color color2) {
        return new Color(
            color1.getR() + color2.getR(),
            color1.getG() + color2.getG(),
            color1.getB() + color2.getB()
        );
    }

    public Color scale(float a) {
        return new Color(
            this.getR() * a,
            this.getG() * a,
            this.getB() * a);
    }

    public static Color multiply(Color color1, Color color2) {
        return new Color(
            color1.getR() * color2.getR(),
            color1.getG() * color2.getG(),
            color1.getB() * color2.getB());
    }

    public Color clamp() {
        return new Color(
            r < MIN ? MIN : r > MAX ? MAX : r,
            g < MIN ? MIN : g > MAX ? MAX : g,
            b < MIN ? MIN : b > MAX ? MAX : b
        );
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
}
