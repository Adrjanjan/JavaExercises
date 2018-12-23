package Lab10;

import java.awt.*;

public class Bauble implements XmasShape {
    private Color outlineColor, fillColor;
    private int x, y;
    private double scale;

    public Bauble(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public Bauble setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
        return this;
    }

    public Bauble setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(fillColor);
        g2d.fillOval(0, 0, 100, 100);
        g2d.setPaint(outlineColor);
        g2d.drawOval(0, 0, 100, 100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}

