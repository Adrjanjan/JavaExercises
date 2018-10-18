package Lab2;

import java.util.Arrays;

public class MatrixTest {

    public static void main(String[] args) throws Exception {
        //testMatrix();
        //testAdd();

        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});

        System.out.println(m);
        int a=2, b=8;
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

        m.reshape(4,4);
        System.out.println(m.dot(m));

        System.out.println(m.add(m));
        System.out.println(m.sub(m));
        System.out.println(m.mul(m));
        System.out.println(m.frobenius());

    }
}
