package Lab10;

import java.awt.*;
import java.util.Arrays;
import java.util.OptionalInt;

public class Branch implements XmasShape {
    private static int[] x_coords = {150, 300, 265, 265, 235, 235, 205, 205, 175, 175, 160, 150, 135, 125, 125,  95,  95,  65,  65,  35,  35,   5};
    private static int[] y_coords = {0  , 105, 100, 105, 100, 105, 100, 105, 100, 105, 100, 105, 100, 105, 100, 105, 100, 105, 100, 105, 100, 105};
    private static final int POLY_CORNERS = x_coords.length;
    private int x_transform, y_transform;
    private double scale;

    public Branch(int x, int y, double scale) {
        x_transform = x;
        y_transform = y;
        this.scale = scale;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x_transform, y_transform);
        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0, 0, new Color(0, 185, 0), 0, 100, new Color(0, 37, 3));
        g2d.setPaint(grad);
        g2d.fillPolygon(x_coords, y_coords, x_coords.length);
    }

    boolean insideBranch(int x, int y) {
        int[] constant = new int [POLY_CORNERS];
        int[] multiple = new int [POLY_CORNERS];
        precalc_values(constant, multiple);

        boolean oddNodes = false, current = x_coords[POLY_CORNERS - 1] > y, previous;
        for (int i = 0; i < POLY_CORNERS; i++) {
            previous = current;
            current = y_coords[i] > y;
            if (current != previous)
                oddNodes ^= (y * multiple[i] + constant[i]) < x;
        }
        return oddNodes;
    }
    // TODO - przetransformowac i przeskalowac y_cords i x_cords o  x_transform, y_transform i scale
    private void precalc_values(int[] constant, int[] multiple) {
        int i, j = POLY_CORNERS - 1;
        for (i = 0; i < POLY_CORNERS; i++) {
            if (y_coords[j] == y_coords[i]) {
                constant[i] = x_coords[i];
                multiple[i] = 0;
            } else {
                constant[i] = x_coords[i] - (y_coords[i] * x_coords[j]) / (y_coords[j] - y_coords[i]) + (y_coords[i] * x_coords[i]) / (y_coords[j] - y_coords[i]);
                multiple[i] = (x_coords[j] - x_coords[i]) / (y_coords[j] - y_coords[i]);
            }
            j = i;
        }
    }

}
