package io.github.timliiang.scene;

public class Material {
    private Color ambient; // ambient constant, percentage of ambient light the material reflects
    private Color diffuse; // diffuse constant, percentage of diffuse light the material reflects
    private Color specular; // specular constant, percentage of specular light the material reflects
    private float shininess; // shininess factor, higher value means shinier material

    public Material(Color ambient, Color diffuse, Color specular, float shininess) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
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

    public float getShininess() {
        return this.shininess;
    }

    public void setShininess(float shininess) {
        this.shininess = shininess;
    }

}
