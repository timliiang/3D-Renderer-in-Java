package io.github.timliiang.gui;

import javax.swing.JFrame;

public class Frame extends JFrame {
    private int width;
    private int height;

    public Frame(int width, int height) {
        this.width = width;
        this.height = height;
        this.setTitle("Ray tracing");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
    }
}
