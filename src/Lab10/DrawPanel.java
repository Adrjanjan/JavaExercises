package Lab10;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    private List<XmasShape> shapes = new ArrayList<>();
    private Color backgroundColor;
    private Image backgroundImg;

    DrawPanel() {
        setBackground(new Color(0, 0, 50));
    }

    public DrawPanel(Image backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public DrawPanel(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
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