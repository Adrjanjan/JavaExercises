package Lab5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathOperationsTest {

    @Test
    public void testToString() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .addProd(2.1, new Power(x, 3))
                .add(new Power(x, 2))
                .addProd(-2, x)
                .add(7);

        System.out.println(exp.toString());
        assertEquals("((2.1 * x^3) + x^2 - (2 * x) + 7)", exp.toString());

    }

    //Utility for next test
    private double f(double x) {
        return x * x * x - 2 * x * x - x + 2;
    }

    @Test
    public void testSimpleEquation() {
        Variable x = new Variable("x");

        Node exp = new Prod(2, new Power(x, 2));

        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            assertEquals(v * v * 2, exp.evaluate(), 0.001);
        }
    }

    @Test
    public void testModerateEquation() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .addProd(-2, new Power(x, 2))
                .addProd(-1, x)
                .add(2);

        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            assertEquals(f(v), exp.evaluate(), 0.001);
        }
    }


    @Test
    public void testCircle() {
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

        x.setValue(-2.0);
        y.setValue(-2.0);
        assertEquals(0.0, circle.evaluate(), 0.0001);
    }

    //Utility for next test
    private double g(double x, double y) {
        return (2.0 * (Math.pow((1 + 2 + 3 + x), 2) + 3) * y * 5.8);
    }

    @Test
    public void testComplicatedEquation() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        x.setValue(5.0);
        y.setValue(2.0);

        Node expr = new Prod(new Constant(2.0),
                new Sum(
                        new Power(
                                new Sum(new Constant(1.0), new Constant(2.0), new Constant(3.0), x),
                                2.0),
                        new Constant(3.0)),
                y).mul(5.8);

        System.out.println(expr.toString());
        System.out.println(expr.diff(x).toString());


        assertEquals("(11.6 * (((x + 6))^2 + 3) * y)", expr.toString());
        assertEquals(g(x.evaluate(), y.evaluate()), expr.evaluate(), 0.0001);
    }

    @Test
    public void testDifferential() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node expr = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .addProd(8, x)
                .addProd(4, y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(expr.toString());

        Node dx = expr.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = expr.diff(y);
        System.out.println(dy.toString());

    }

    @Test
    public void testAdvancedFunctions() {

        Node expr = new Sum(new Sin(Math.PI), new Cos(Math.PI + 1), new Log(Math.E, new Exp(Math.PI)));

        assertEquals("(sin(3.14159) + cos(4.14159) + log_2.71828^(e^(3.14159)))", expr.toString());

    }

}