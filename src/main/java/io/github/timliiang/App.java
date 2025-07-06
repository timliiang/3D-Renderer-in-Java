package io.github.timliiang;

import io.github.timliiang.gui.*;
import io.github.timliiang.math.*;
import io.github.timliiang.scene.*;

import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App {
    final static int WIDTH = 256; // 1920 , 256
    final static int HEIGHT = 192; // 1080 , 192

    public static void main( String[] args ) {

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Frame frame = new Frame(WIDTH, HEIGHT);

        Sphere sphere1 = new Sphere(
            new Vec3(30f, 30f, 20f), 
            20f,
            new Color(1f, 0f, 0f));
        Sphere sphere2 = new Sphere(
            new Vec3(70f, 50f, 60f), 
            50f,
            new Color(0f, 1f, 1f));
        Sphere sphere3 = new Sphere(
            new Vec3(120f, 70f, 100f), 
            60f,
            new Color(0f, 0f, 1f));

        // Screen
        Vec3 x1 = new Vec3(1f, 0.75f, 0f);
        Vec3 x2 = new Vec3(-1f, 0.75f, 0f);
        Vec3 x3 = new Vec3(1f, -0.75f, 0f);
        Vec3 x4 = new Vec3(-1f, -0.75f, 0f);

        Vec3 c = new Vec3(0f, 0f, -1f); 

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                float alpha = x / (float)(WIDTH - 1); 
                float beta = y / (float)(HEIGHT - 1);
                Vec3 t = Vec3.lerp(x1, x2, alpha);
                Vec3 b = Vec3.lerp(x3, x4, alpha);
                Vec3 p = Vec3.lerp(t, b, beta);
                Ray ray = new Ray(p, Vec3.sub(p, c));

                image.setRGB(x, (HEIGHT - 1 - y), new Color(x / 255f, y / 255f, 100 / 255f).getRGB());
            }
        }

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }
}
