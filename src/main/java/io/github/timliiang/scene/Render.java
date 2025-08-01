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

    private static final Color AMBIENT = new Color(0.5f, 0.5f, 0.5f); // i_a, ambient intensity
    private static Vec3 camera = new Vec3(0f, 0f, -1f);
    private static final int REFLECTION_DEPTH = 3; // recursive ray tracing depth for reflections

    // For anti-aliasing
    private static final int SAMPLES_PER_DIRECTION = 3;
    private static final int SAMPLES = SAMPLES_PER_DIRECTION * SAMPLES_PER_DIRECTION;

    private static ImagePlane imagePlane = new ImagePlane(
        new Vec3(1f, 0.75f, 0f),
        new Vec3(-1f, 0.75f, 0f),
        new Vec3(1f, -0.75f, 0f),
        new Vec3(-1f, -0.75f, 0f)
    );

    private static Sphere[] spheres = {
        new Sphere(
            new Vec3(0f, 0f, 10f), 
            2f,
            new Material(
                new Color(0.204f, 0.1f, 0f),
                new Color(0.8f, 0.4f, 0f),
                new Color(1f, 1f, 1f),
                80,
                new Color(0.1f, 0.2f, 0.3f)
        )),
        new Sphere(
            new Vec3(15f, 0f, 20f), 
            3f,
            new Material(
                new Color(0.07f, 0.205f, 0.7f),
                new Color(0.8f, 0.4f, 0f),
                new Color(1f, 1f, 1f),
                80,
                new Color(0.1f, 0.2f, 0.3f)
        )),
        new Sphere(
            new Vec3(-12f, 6f, 20f),
            5f,
            new Material(
                new Color(0.07f, 0.8f, 0.07f),
                new Color(0.8f, 0.4f, 0f),
                new Color(1f, 1f, 1f),
                50,
                new Color(0.1f, 0.1f, 0.1f)
        )),
        new Sphere(
            new Vec3(22.5f, 20f, 25f),
            0f, // 10
            new Material(
                new Color(0.07f, 0.8f, 0.07f),
                new Color(0.8f, 0.4f, 0f),
                new Color(1f, 1f, 1f),
                50,
                new Color(0f, 0f, 0f)
        ))
    };

    private static Light[] lights = {
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
    

    /*
     * render :)
     */
    public static void render() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Frame frame = new Frame(WIDTH, HEIGHT);

        // loop through each pixel
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Color color = new Color(0f, 0f, 0f);
                color = tracePixel(x, y);

                // Paint pixel
                image.setRGB(x, y, color.getRGB());
            }
        }

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }

    /*
     * This method traces through each pixel and determines the color based from the ray it casts
     * 
     */
    public static Color tracePixel(int x, int y) {
        Color sum = new Color(0f, 0f,0f);

        // anti-aliasing
        for (int aSample = 0; aSample < SAMPLES_PER_DIRECTION; aSample++) {
            for (int bSample = 0; bSample < SAMPLES_PER_DIRECTION; bSample++) {
                // background color
                Color tempColor = new Color(0f, 0f, 0f);

                float alpha = x / (float) WIDTH; 
                float beta = y / (float) HEIGHT;
                alpha += aSample * (1 / (float) WIDTH) / SAMPLES_PER_DIRECTION;
                beta += bSample * (1 / (float) HEIGHT) / SAMPLES_PER_DIRECTION;

                Vec3 t = Vec3.lerp(
                    imagePlane.getX1(), 
                    imagePlane.getX2(), 
                    alpha
                );
                Vec3 b = Vec3.lerp(
                    imagePlane.getX3(), 
                    imagePlane.getX4(), 
                    alpha
                );

                Vec3 p = Vec3.lerp(t, b, beta);
                Ray ray = new Ray(p, p.sub(camera));

                Float smallestT = Float.NaN;
                Sphere smallestSphere = null;

                // Find closest object the ray intersects if any
                for (int s = 0; s < spheres.length; s++) {
                    Optional<Float> opt = spheres[s].intersection(ray);
                    if (opt.isPresent() && (smallestT.isNaN() || opt.get() < smallestT)) {
                        smallestT = opt.get();
                        smallestSphere = spheres[s];
                    }
                }

                // If ray cast has intersection
                if (!smallestT.isNaN()) {
                    // Phong's Illumination Model
                    tempColor = rayColor(smallestT, smallestSphere, ray, REFLECTION_DEPTH);
                }

                // Adding colour for averaging samples -- anti-aliasing
                sum = sum.add(tempColor);
            }
        } // anti-aliasing loop end
        return sum.scale(1 / (float) SAMPLES);
    }

    /*
     * This method determines the color of a ray by using Phong's Illumination Model.
     *  The color consists of three components being Ambient, Diffuse, and Specular.
     * 
     * @param depth, recursion depth
     */
    public static Color rayColor(Float t, Sphere sphere, Ray ray, int depth) {
        Material smallestMat = sphere.getMaterial();
        Vec3 pSphere = ray.getOrigin().add(ray.getDirection().scale(t));
        Vec3 surfNorm = pSphere.sub(sphere.getCenter()).normalize();

        Color color = smallestMat.getAmbient().multiply(AMBIENT);
        Color diffuseComp = null;
        Color specComp = null;

        // For reflection
        if (depth != 0) {
            Vec3 reflVecV = ray.getDirection().scale(-1).normalize();
            Vec3 reflVecR = surfNorm.scale(surfNorm.dot(reflVecV) * 2).sub(reflVecV);
            Ray reflectRay = new Ray(pSphere, reflVecR);
            Color reflectColor = rayColor(t, sphere, reflectRay, depth - 1);
            reflectColor = reflectColor.multiply(smallestMat.getReflectivity());
            color = color.add(reflectColor);
        }

        for (int i = 0; i < lights.length; i++) {
            // Check if in shadow, if it is then dont add diffuse and specular values
            Ray shadow = new Ray(pSphere, lights[i].getLocation().sub(pSphere));
            if (inShadow(shadow, sphere)) continue;

            Vec3 lightVec = lights[i].getLocation().sub(pSphere).normalize();
            float temp = surfNorm.dot(lightVec);
            if (temp < 0) continue;

            // Calculate diffuse component
            diffuseComp = smallestMat.getDiffuse().multiply(lights[i].getDiffuse()).scale(temp);
            Vec3 reflVec = surfNorm.scale(2 * temp).sub(lightVec);
            Vec3 viewVec = camera.sub(pSphere).normalize();

            // Calculate specular component
            specComp = smallestMat.getSpecular().multiply(lights[i].getSpecular());
            specComp = specComp.scale(
                (float) Math.pow(
                    viewVec.dot(reflVec), 
                    smallestMat.getShininess()));

            // Add the color components
            color = color.add(diffuseComp);
            color = color.add(specComp);
            color = color.clamp();
        }

        return color;
    }

    /*
     * shadows
     */
    public static boolean inShadow(Ray shadow, Sphere sphere) {
        for (int s = 0; s < spheres.length; s++) {
            if (spheres[s] == sphere) continue;

            Optional<Float> t = spheres[s].intersection(shadow);
            if (t.isPresent() && t.get() < 1) {
                return true;
            }
        }
        return false;
    }

}
