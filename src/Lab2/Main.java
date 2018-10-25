package Lab2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        Matrix m = new Matrix(new double[][]{{1,2},{3,4},{5,6},{7, 8}});

        System.out.println(m);
        int a=4, b=2;
        m.reshape(a, b);

        System.out.println(Arrays.deepToString(m.asArray()));

        System.out.println("Shape " + Arrays.toString(m.shape()));

        System.out.println(m.add(m));
        System.out.println(m.sub(m));
        System.out.println(m.mul(m));
        System.out.println(m.div(m));
        double d = 1.5;
        System.out.println(m.add(d));
        System.out.println(m.sub(d));
        System.out.println(m.mul(d));
        System.out.println(m.div(d));

        System.out.println(m.reshape(4,4).dot(m));

        System.out.println(m.add(m));
        System.out.println(m.sub(m));
        System.out.println(m.mul(m));
        System.out.println(m.frobenius());

    }
}
