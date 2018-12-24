package Lab10;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    private List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        this(new Color(0 , 0 , 50));
    }

    public DrawPanel(Color backgroundColor) {
        setBackground(backgroundColor);
    }

    public void drawImage(Graphics g, Image img) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (XmasShape shape : shapes) {
            shape.draw((Graphics2D) g);
        }
    }

    public void addShape(XmasShape shape) {
        if (shape == null) return;
        this.shapes.add(shape);
    }
}