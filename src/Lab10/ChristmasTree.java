package Lab10;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ChristmasTree {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Choinka");
        DrawPanel panel = new DrawPanel();
        addBackground(panel);
        addTree(panel);
        addStar(panel);
        addLights(panel);
        addBaubles(panel);

        frame.setContentPane(panel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private static void addBackground(DrawPanel panel) {
        Random rand = new Random();
        for (int i = 0; i < 40; ++i) {
            panel.addShape(new Star(rand.nextInt(1000), rand.nextInt(700), 0.7, 0.7, new Color(0xFEFF00)));
        }
        panel.addShape(new XmasShape() {
            @Override
            public void transform(Graphics2D g2d) {
            }


            @Override
            public void render(Graphics2D g2d) {
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(new Color(0x646464));
                g2d.fillPolygon(new int[]{0, 150, 190, 240, 400, 600, 800, 1000, 1000, 0},
                        new int[]{600, 400, 450, 390, 500, 300, 500, 400, 700, 700}, 10);
            }
        });

        panel.addShape(new XmasShape() {
            @Override
            public void transform(Graphics2D g2d) {
            }

            @Override
            public void render(Graphics2D g2d) {
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(new Color(0x0F0F0F));
                g2d.fillOval(590, 550, 200, 60);
            }
        });

    }

    private static void addTree(DrawPanel panel) {
        panel.addShape(new XMasTree(500, 150, 1.5, 1.5));
    }

    private static void addLights(DrawPanel panel) {
        for (int i = 0; i < 10; ++i) {
            panel.addShape(new Lights(607 + 10 * i, 200 + 3 * i, 0.5, new Color(0xFF0000)));
            panel.addShape(new Lights(607 + 7 * i, 200 - 2 * i, 0.5, new Color(0xFF0000)));
        }
        panel.addShape(new Lights(607 + 100, 200 + 30, 0.5, new Color(0xFF0000)));

        for (int i = 0; i < 18; ++i) {
            panel.addShape(new Lights(575 + 10 * i, 350 + 2 * i, 0.5, new Color(0x094BFF)));
            panel.addShape(new Lights(575 + 7 * i, 350 - 2 * i, 0.5, new Color(0x094BFF)));
        }
        panel.addShape(new Lights(575 + 100, 350 + 20, 0.5, new Color(0x094BFF)));

        for (int i = 0; i < 19; ++i) {
            panel.addShape(new Lights(555 + 10 * i, 470 + 2 * i, 0.5, new Color(0xFEFF00)));
            panel.addShape(new Lights(555 + 7 * i, 470 - 3 * i, 0.5, new Color(0xFEFF00)));
        }
        for (int i = 19; i < 23; ++i) {
            panel.addShape(new Lights(555 + 10 * i, 470 + 2 * i, 0.5, new Color(0xFEFF00)));
        }
    }

    private static void addStar(DrawPanel panel) {
        panel.addShape(new Star(650, 150, 2, 2, new Color(200, 194, 0)));
    }

    private static void addBaubles(DrawPanel panel) {
        panel.addShape(new Bauble(650, 220, 1,
                new Color(0xFF4C0069),
                new Color(0xFF2A0030)));

        panel.addShape(new Bauble(675, 450, 1,
                new Color(0xA4BD1300),
                new Color(0xF5300400)));

        panel.addShape(new Bauble(650, 370, 1,
                new Color(0xA45EBD22),
                new Color(0xF515300A)));

        panel.addShape(new Bauble(640, 290, 1,
                new Color(0xA4555EBD),
                new Color(0xF50B0B30)));

        panel.addShape(new Bauble(615, 325, 1,
                new Color(0xB64C0069),
                new Color(0xF52A0030)));

        panel.addShape(new Bauble(690, 350, 1,
                new Color(0xA4BD1300),
                new Color(0xF5300400)));

        panel.addShape(new Bauble(610, 430, 1,
                new Color(0xA45EBD22),
                new Color(0xF515300A)));

        panel.addShape(new Bauble(590, 490, 1,
                new Color(0xA4555EBD),
                new Color(0xF50B0B30)));
    }
}
