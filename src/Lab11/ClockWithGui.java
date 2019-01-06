package Lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

import static java.awt.BasicStroke.*;

public class ClockWithGui extends JPanel {

    private LocalTime time = LocalTime.now();

    private ClockWithGui() {
        setBackground(new Color(0x6792FF));
        new Clock().start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform startPos = g2d.getTransform();
        g2d.translate(250, 250);

        drawHoursNumbers(g2d);
        drawClockLines(g2d);

        drawHourTip(g2d);
        drawMinuteTip(g2d);
        drawSecondTip(g2d);

        g2d.setTransform(startPos);
    }

    private void drawClockLines(Graphics2D g2d) {
        AffineTransform at, saveAT = g2d.getTransform();
        g2d.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_MITER));
        for (int i = 0; i <= 35; ++i) {
            if (i % 3 == 1) {
                continue;
            }
            at = new AffineTransform(saveAT);
            at.rotate(2 * Math.PI / 36 * i);
            g2d.setTransform(at);
            g2d.drawLine(85, 85, 90, 90);
            g2d.setTransform(saveAT);
        }
        g2d.setTransform(saveAT);
    }

    private void drawHoursNumbers(Graphics2D g2d) {
        AffineTransform saveAT = g2d.getTransform();
        String text;
        AffineTransform at;
        for (int i = 1; i <= 12; ++i) {
            at = new AffineTransform();
            at.rotate(2 * Math.PI / 12 * i);
            text = Integer.toString(i);
            Point2D src = new Point2D.Float(-(float) g2d.getFontMetrics().stringWidth(text), -120);
            Point2D trg = new Point2D.Float();
            at.transform(src, trg);

            g2d.drawString(text, (int) trg.getX(), (int) trg.getY());
        }
        g2d.setTransform(saveAT);
    }

    private void drawHourTip(Graphics2D g2d) {
        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getHour() % 12 * 2 * Math.PI / 12);
        g2d.setStroke(new BasicStroke(5, CAP_BUTT, JOIN_MITER));
        g2d.drawLine(0, 0, 0, -100);
        g2d.setTransform(saveAT);
    }

    private void drawMinuteTip(Graphics2D g2d) {
        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getMinute() % 60 * 2 * Math.PI / 60);
        g2d.setStroke(new BasicStroke(3, CAP_BUTT, JOIN_MITER));
        g2d.drawLine(0, 0, 0, -100);
        g2d.setTransform(saveAT);

    }

    private void drawSecondTip(Graphics2D g2d) {
        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getSecond() % 60 * 2 * Math.PI / 60);
        g2d.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_MITER));
        g2d.drawLine(0, 0, 0, -100);
        g2d.setTransform(saveAT);
    }

    class Clock extends Thread {

        @Override
        public void run() {
            while (true) {
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n", time.getHour(), time.getMinute(), time.getSecond());
                try {
                    sleep(1000);
                } catch (InterruptedException ignored) {
                }
                repaint();
            }
        }
    }
}
