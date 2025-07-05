package io.github.timliiang;

import io.github.timliiang.math.*;
import io.github.timliiang.scene.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Hello world!
 *
 */
public class App {
    final static int WIDTH = 256; // 1920 , 256
    final static int HEIGHT = 192; // 1080 , 192

    public static void main( String[] args ) {

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Ray tracing");
        //frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1:0.75 is aspect ratio 4:3
        // for 1920 x 1080, switch to 16:9 or 1:0.5625
        Vec3 x1 = new Vec3(1f, 0.75f, 0f);
        Vec3 x2 = new Vec3(-1f, 0.75f, 0f);
        Vec3 x3 = new Vec3(1f, -0.75f, 0f);
        Vec3 x4 = new Vec3(-1f, -0.75f, 0f);
        
        Vec3 c = new Vec3(0f, 0f, -1f); 

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                float alpha = x / (float)(WIDTH - 1); 
                float beta = y / (float)(HEIGHT - 1);
                Vec3 t = Vec3.add(x1.scale(1 - alpha), x2.scale(alpha));
                Vec3 b = Vec3.add(x3.scale(1 - alpha), x4.scale(alpha));
                Vec3 p = Vec3.add(t.scale(1 - beta), b.scale(beta));
                Ray ray = new Ray(p, Vec3.sub(p, c));

                image.setRGB(x, (HEIGHT - 1 - y), new Color(x, y, 100).getRGB());
            }
        }

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }
}
