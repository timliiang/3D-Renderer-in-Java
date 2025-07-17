package io.github.timliiang.scene;

public class Material {
    private Color ambient; // k_a, ambient constant, percentage of ambient light the material reflects
    private Color diffuse; // k_d, diffuse constant, percentage of diffuse light the material reflects
    private Color specular; // k_s, specular constant, percentage of specular light the material reflects
    private int shininess; // alpha, shininess factor, higher value means shinier material
    private Color reflectivity; // k_r, reflectivity of a material. If reflectivity is high then set diffuse for simulation

    public Material(Color ambient, Color diffuse, Color specular, int shininess, Color reflectivity) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
        this.reflectivity = reflectivity;
    }

    public Color getAmbient() {
        return this.ambient;
    }

    public void setAmbient(Color ambient) {
        this.ambient = ambient;
    }

    public Color getDiffuse() {
        return this.diffuse;
    }

    public void setDiffuse(Color diffuse) {
        this.diffuse = diffuse;
    }

    public Color getSpecular() {
        return this.specular;
    }

    public void setSpecular(Color specular) {
        this.specular = specular;
    }

    public int getShininess() {
        return this.shininess;
    }

    public void setShininess(int shininess) {
        this.shininess = shininess;
    }

    public Color getReflectivity() {
        return this.reflectivity;
    }

    public void setReflectivity(Color reflectivity) {
        this.reflectivity = reflectivity;
    }


}
