package Lab10;

import java.awt.*;

public class Bauble implements XmasShape {
    Color outlineColor, fillColor;
    private int x, y;
    private double scale;

    @Override
    public void render(Graphics2D g2d) {
        g2d.fillOval(0, 0, 100, 100);
        GradientPaint grad = new GradientPaint(0, 0, new Color(0, 195, 18), 0, 100, new Color(0, 10, 0));
        g2d.setPaint(grad);
        g2d.drawOval(0, 0, 100, 100);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}

