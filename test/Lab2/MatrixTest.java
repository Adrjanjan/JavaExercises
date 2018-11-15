package Lab2;

import org.junit.Test;

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
        Matrix m = new Matrix(2, 2);
        double[][] exp = {{0, 0}, {0, 0}};
        assertEquals(m.toString(),"[[0.0, 0.0],\n[0.0, 0.0]]");


        double[][] exp2 = {{1, 2}, {2, 3}, {3}};
        m = new Matrix(exp2);
        assertEquals(m.toString(),"[[1.0, 2.0],\n[2.0, 3.0],\n[3.0, 0.0]]");

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
        double[][] exp = {{1, 2}, {2, 3}, {3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.add(m).asArray(), new double[][]{{2,4},{4,6},{6,0}});
    }

    @org.junit.Test
    public void subMatrix() {
        double[][] exp = {{1, 2}, {2, 3}, {3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.sub(m).asArray(), new double[][]{{0,0},{0,0},{0,0}});
    }

    @org.junit.Test
    public void mulMatrix() {
        double[][] exp = {{1, 2}, {2, 3}, {3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.mul(m).asArray(), new double[][]{{1,4},{4,9},{9,0}});
    }

    @org.junit.Test
    public void divMatrix() {
        double[][] exp = {{1, 2}, {2, 3}, {3, 4}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.div(m).asArray(), new double[][]{{1,1},{1,1},{1,1}});
    }

    @org.junit.Test
    public void addDouble() {
        double[][] exp = {{1, 2}, {2, 3}, {3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.add(2.5).asArray(), new double[][]{{3.5,4.5},{4.5,5.5},{5.5,2.5}});
    }

    @org.junit.Test
    public void subDouble() {
        double[][] exp = {{1, 2}, {2, 3}, {3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.sub(1.5).asArray(), new double[][]{{-0.5,0.5},{0.5,1.5},{1.5,-1.5}});
    }

    @org.junit.Test
    public void mulDouble() {
        double[][] exp = {{1, 2}, {2, 3}, {3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.mul(2).asArray(), new double[][]{{2,4},{4,6},{6,0}});
    }

    @org.junit.Test
    public void divDouble() {
        double[][] exp = {{1, 2}, {2, 3}, {3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.div(2).asArray(), new double[][]{{0.5,1},{1,1.5},{1.5,0}});
    }

    @org.junit.Test
    public void dot() {
        double[][] exp = {{1, 2}, {2, 3}};
        Matrix m = new Matrix(exp);
        assertArrayEquals(m.dot(m).asArray(), new double[][]{{5,8},{8,13}});
    }

    @org.junit.Test
    public void frobenius() {
        double[][] exp = {{1, 2}, {2, 3}};
        Matrix m = new Matrix(exp);
        assertEquals(4.2426406871193, m.frobenius(), 0.1 );

        exp = new double[][]{{1, 1}, {2, 3}, {3, 1}};
        m = new Matrix(exp);
        assertEquals(5, m.frobenius(), 0.1 );
    }

    @Test
    public void getColumn() {
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = m.getColumn(0);
        Matrix m1 = new Matrix(1, m.shape()[0]);
        m1.set(0, 0, 1);
        m1.set(0, 1, 4);
        m1.set(0, 2, 7);


        System.out.print(Arrays.deepToString(col.asArray()));

    }
}





























