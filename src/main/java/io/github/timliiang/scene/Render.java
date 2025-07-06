package io.github.timliiang.scene;

import java.awt.image.BufferedImage;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import io.github.timliiang.App;
import io.github.timliiang.gui.*;
import io.github.timliiang.math.*;

public class Render {
    private static int WIDTH = App.WIDTH;
    private static int HEIGHT = App.HEIGHT;
    
    public static void render() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Frame frame = new Frame(WIDTH, HEIGHT);

        Sphere[] arr = {
            new Sphere(
                new Vec3(0f, 0f, 20f), 
                10f,
                new Color(1f, 0f, 0f)),
            new Sphere(
                new Vec3(70f, 50f, 60f), 
                20f,
                new Color(0f, 1f, 0f)),
            new Sphere(
                new Vec3(-50f, 20f, 100f), 
                30f,
                new Color(0f, 0f, 1f))
        };

        
        // Screen
        Vec3 x1 = new Vec3(1f, 0.75f, 0f); // + +
        Vec3 x2 = new Vec3(-1f, 0.75f, 0f); // - +
        Vec3 x3 = new Vec3(1f, -0.75f, 0f); // + -
        Vec3 x4 = new Vec3(-1f, -0.75f, 0f); // - -

        Vec3 c = new Vec3(0f, 0f, -1f); 

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                float alpha = x / (float)(WIDTH - 1); 
                float beta = y / (float)(HEIGHT - 1);
                Vec3 t = Vec3.lerp(x1, x2, alpha);
                Vec3 b = Vec3.lerp(x3, x4, alpha);
                Vec3 p = Vec3.lerp(t, b, beta);
                Ray ray = new Ray(p, Vec3.sub(p, c));

                Color color = new Color(0f, 0f, 0f);
                Float smallest = Float.NaN;

                for (int s = 0; s < arr.length; s++) {
                    Optional<Float> opt = arr[s].intersection(ray);
                    if (opt.isPresent() && (smallest.isNaN() || opt.get() < smallest)) {
                        smallest = opt.get();
                        color = arr[s].getColor();
                    }
                }

                //image.setRGB(x, (HEIGHT - 1 - y), new Color((float) x / WIDTH, (float) y / HEIGHT, 100 / 255f).getRGB());
                image.setRGB(x, y, color.getRGB());
            }
        }

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }

}
