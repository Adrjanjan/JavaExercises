package Lab10;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ChristmasTree {
    private static final int NUMBER_OF_BAUBLES = 30;

    public static void main(String[] args) {
        Random rand = new Random();
        JFrame frame = new JFrame("Choinka");
        DrawPanel panel = new DrawPanel();
        XMasTree tree = new XMasTree();
        panel.addShape(tree);
        for (int i = 0; i< NUMBER_OF_BAUBLES; ++i) {
            int x = rand.nextInt(500);
            int y = rand.nextInt(350);
            while (!tree.insideTree(x, y)) {
                x = rand.nextInt(500);
                y = rand.nextInt(350);
            }
            panel.addShape(new Bauble(x, y, 0.1)
                    .setFillColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)))
                    .setOutlineColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))));
        }
        frame.setContentPane(panel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
