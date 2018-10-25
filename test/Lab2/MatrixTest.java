package Lab2;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void asArray() {
        Matrix m = new Matrix(2, 2);
        double[][] exp = {{0, 0}, {0, 0}};
        assertArrayEquals(m.asArray(), exp);


        double[][] exp2 = {{1, 2}, {2, 3}, {3, 4}};
        m = new Matrix(exp2);
        assertArrayEquals(m.asArray(), exp2);
    }

    @org.junit.Test
    public void get() {
        double[][] exp = {{1, 2}, {2, 3}, {3, 4}};
        Matrix m = new Matrix(exp);

        assertEquals( 1.0, m.get(0, 0), 0);
    }

    @org.junit.Test
    public void set() {
        double[][] exp = {{1, 2}, {2, 3}, {3, 4}};
        Matrix m = new Matrix(exp);
        m.set(0,0, 5.5);

        assertEquals( 5.5, m.get(0, 0), 0);
    }

    @org.junit.Test
    public void testToString() {


    }

    @org.junit.Test
    public void reshape() {
        Matrix m = new Matrix(5, 2);

        try{
            m.reshape(3,3);
        } catch(Exception exp){
            return;
        }
        fail("Wrong dimensions exception not caught. ");

    }

    @org.junit.Test
    public void shape() {
        Matrix m = new Matrix(2, 2);
        int[] exp = {2, 2};
        assertArrayEquals(m.shape(), exp);


        double[][] exp2 = new double[][]{{1, 2}, {2, 3}, {3, 4}};
        m = new Matrix(exp2);
        assertArrayEquals(m.shape(), new int[]{3, 2});
    }

    @org.junit.Test
    public void addMatrix() {
    }

    @org.junit.Test
    public void subMatrix() {
    }

    @org.junit.Test
    public void mulMatrix() {
    }

    @org.junit.Test
    public void divMatrix() {
    }

    @org.junit.Test
    public void addDouble() {
    }

    @org.junit.Test
    public void subDouble() {
    }

    @org.junit.Test
    public void mulDouble() {
    }

    @org.junit.Test
    public void divDouble() {
    }

    @org.junit.Test
    public void dot() {
    }

    @org.junit.Test
    public void frobenius() {
    }
}