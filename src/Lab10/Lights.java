package Lab10;

import java.awt.*;

public class Lights implements XmasShape {
    private int x, y;
    private double scale;
    private Color lightsColor;

    public Lights(int x, int y, double scale, Color lightsColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.lightsColor = lightsColor;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int n = 15;
        g2d.setPaint(lightsColor);
        for (int i = 0; i < n; i++) {
            g2d.drawLine(5, 5, 10, 10);
            g2d.translate(5, 5);
            g2d.rotate(2 * Math.PI / n);
            g2d.translate(-5, -5);
        }
    }
}
