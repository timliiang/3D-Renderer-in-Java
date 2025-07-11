package io.github.timliiang.gui;

import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Frame extends JFrame implements MouseMotionListener {

    public Frame(int width, int height) {
        this.setTitle("Ray tracing");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);

        addMouseMotionListener(this);
    }

    public void mouseDragged(MouseEvent e) {
        return;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("x: " + e.getX() + ", y: " + e.getY());
    }
    
}
