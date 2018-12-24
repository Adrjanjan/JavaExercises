package Lab10;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class XMasTree implements XmasShape {
    private List<XmasShape> tree = new ArrayList<>();
    private int x, y;
    private double scaleX, scaleY;

    public XMasTree(int x, int y, double scaleX, double scaleY) {
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        tree.add(new Trunk(85, 240, 1, 1, new Color(0xD1EAFF), new Color(0x562E00)));
        tree.add(new Branch(0, 150, 1, 1, new Color(0xD1EAFF), new Color(0x128A0F)));
        tree.add(new Branch(20, 75, 0.8, 0.9, new Color(0xD1EAFF), new Color(0x128A0F)));
        tree.add(new Branch(40, 0, 0.6, 0.8, new Color(0xD1EAFF), new Color(0x128A0F)));
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scaleX, scaleY);
    }

    @Override
    public void render(Graphics2D g2d) {

    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform saveAt = g2d.getTransform();
        transform(g2d);
        for (XmasShape s : tree) {
            s.draw(g2d);
        }
        g2d.setTransform(saveAt);
    }

    static class Trunk implements XmasShape {
        private int x, y;
        private double scaleX, scaleY;
        private Color color, snowColor;

        Trunk(int x, int y, double scaleX, double scaleY, Color snowColor, Color color) {
            this.x = x;
            this.y = y;
            this.scaleX = scaleX;
            this.scaleY = scaleY;
            this.color = color;
            this.snowColor = snowColor;
        }

        @Override
        public void transform(Graphics2D g2d) {
            g2d.translate(x, y);
            g2d.scale(scaleX, scaleY);
        }

        @Override
        public void render(Graphics2D g2d) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(color);
            g2d.fillRect(0, 0, 30, 50);
            g2d.setPaint(snowColor);
            g2d.fillRect(0, 0, 5, 50);
        }
    }
}

