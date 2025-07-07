package io.github.timliiang.scene;

import io.github.timliiang.math.Vec3;

public class Light {
    private Vec3 location; // location in world 
    private Color diffuse; // diffuse intensity
    private Color specular; // specular intensity

    public Light(Vec3 location, Color diffuse, Color specular) {
        this.location = location;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public Vec3 getLocation() {
        return this.location;
    }

    public void setLocation(Vec3 location) {
        this.location = location;
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

}
