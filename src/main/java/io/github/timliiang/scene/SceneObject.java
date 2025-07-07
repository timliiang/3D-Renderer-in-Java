package io.github.timliiang.scene;

import io.github.timliiang.math.Ray;

import java.util.Optional;

public interface SceneObject {
    public Optional<Float> intersection(Ray r);
}
