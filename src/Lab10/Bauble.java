package Lab10;

import java.awt.*;

public class Bauble implements XmasShape {
    private Color outlineColor, fillColor;
    private int x, y;
    private double scale;

    Bauble(int x, int y, double scale, Color fillColor, Color outlineColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(fillColor);
        g2d.fillOval(0, 0, 20, 20);
        g2d.setPaint(outlineColor);
        g2d.drawOval(0, 0, 20, 20);
    }
}

