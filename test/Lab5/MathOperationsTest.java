package Lab5;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;
//TODO - przerobić to na testy
public class MathOperationsTest {

    @Test
    void buildAndPrint() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .addProd(2.1, new Power(x, 3))
                .add(new Power(x, 2))
                .addProd(-2, x)
                .add(7);
        System.out.println(exp.toString());

    }
    @Test
    void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .addProd(-2, new Power(x, 2))
                .addProd(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    @Test
    void defineCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .addProd(8, x)
                .addProd(4, y)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100 * (Math.random() - .5);
        double yv = 100 * (Math.random() - .5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s", xv, yv, (fv < 0 ? "wewnątrz" : "na zewnątrz"), circle.toString()));
    }
}