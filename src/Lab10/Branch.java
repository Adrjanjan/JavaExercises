package Lab10;

import java.awt.*;

public class Branch implements XmasShape {
    private Color snowColor, fillColor;
    private int x, y;
    private double scaleX, scaleY;

    public Branch(int x, int y, double scaleX, double scaleY, Color snowColor, Color fillColor) {
        this.snowColor = snowColor;
        this.fillColor = fillColor;
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scaleX, scaleY);
    }

    @Override
    public void render(Graphics2D g2d) {
        int[] branch_x = {0, 100, 200};
        int[] branch_y = {100, 0, 100};
        int[] snow_x = {0, 100, 30};
        int[] snow_y = {100, 0, 100};

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(fillColor);
        g2d.fillPolygon(branch_x, branch_y, branch_x.length);

        g2d.setPaint(snowColor);
        g2d.fillPolygon(snow_x, snow_y, snow_x.length);
    }
}
