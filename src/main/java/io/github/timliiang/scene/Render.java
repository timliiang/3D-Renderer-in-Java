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
        Color ambient = new Color(0.5f, 0.5f, 0.5f); // i_a, ambient intensity

        Sphere[] spheres = {
            new Sphere(
                new Vec3(0f, 0f, 10f), 
                2f,
                new Material(
                    new Color(0.204f, 0.1f, 0f),
                    new Color(0.8f, 0.4f, 0f),
                    new Color(1f, 1f, 1f),
                    80
            )),
            new Sphere(
                new Vec3(15f, 0f, 20f), 
                3f,
                new Material(
                    new Color(0.07f, 0.205f, 0.7f),
                    new Color(0.8f, 0.4f, 0f),
                    new Color(1f, 1f, 1f),
                    80
            )),
            new Sphere(
                new Vec3(-12f, 6f, 20f), //-50, 20, 100
                5f,
                new Material(
                    new Color(0.07f, 0.8f, 0.07f),
                    new Color(0.8f, 0.4f, 0f),
                    new Color(1f, 1f, 1f),
                    50
            ))
        };

        Light[] lights = {
            new Light(
                new Vec3(30f, 40f, 30f),
                new Color(0.6f, 0.8f, 0.8f),    // warm orange/yellow diffuse
                new Color(0.8f, 0.9f, 1f)    // warm specular
            ),
            new Light(
                new Vec3(-100f, 60f, -30f),
                new Color(0.6f, 0.8f, 0.8f),
                new Color(0.8f, 0.9f, 1f)
            )
        };

        
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
                Ray ray = new Ray(p, p.sub(c));

                Color color = new Color(0f, 0f, 0f); // pixel colour

                Float smallestT = Float.NaN;
                Sphere smallestSphere = null;
                Material smallestMat = null;

                for (int s = 0; s < spheres.length; s++) {
                    Optional<Float> opt = spheres[s].intersection(ray);
                    if (opt.isPresent() && (smallestT.isNaN() || opt.get() < smallestT)) {
                        smallestT = opt.get();
                        smallestSphere = spheres[s];
                    }
                }

                // proj 3 step 4
                if (!smallestT.isNaN()) {
                    smallestMat = smallestSphere.getMaterial();
                    Vec3 pSphere = ray.getOrigin().add(ray.getDirection().scale(smallestT));
                    Vec3 surfNorm = pSphere.sub(smallestSphere.getCenter()).normalize();
                    color = Color.multiply(smallestMat.getAmbient(), ambient);
                    Color diffuseComp = null;
                    Color specComp = null;
                    for (int i = 0; i < lights.length; i++) {
                        Vec3 lightVec = lights[i].getLocation().sub(pSphere).normalize();
                        float temp = surfNorm.dot(lightVec);
                        if (temp < 0) continue;
                        diffuseComp = Color.multiply(smallestMat.getDiffuse(), lights[i].getDiffuse()).scale(temp);
                        Vec3 reflVec = surfNorm.scale(2 * temp).sub(lightVec);
                        Vec3 viewVec = c.sub(pSphere).normalize();
                        specComp = Color.multiply(smallestMat.getSpecular(), lights[i].getSpecular()).scale(
                                (float) Math.pow(
                                    viewVec.dot(reflVec), 
                                    smallestMat.getShininess()));
                        color = color.add(diffuseComp);
                        color = color.add(specComp);
                        color = color.clamp();
                    }
                    
                }
                image.setRGB(x, y, color.getRGB());
            }
        }

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }

}
