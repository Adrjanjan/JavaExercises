package Lab2;

public class Matrix {
    private double[]data;
    private int rows;
    private int cols;

    Matrix(int rows, int cols) throws ArrayIndexOutOfBoundsException{
        if (rows <= 0 || cols <= 0)
            throw new ArrayIndexOutOfBoundsException(String.format("Rows or cols is not positive: \nRows: %d\nCols: %d", rows,cols));
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }
    Matrix(double[][] d){
        rows = d.length;
        int max =0;
        for(int i =0; i< rows; ++i){
            max = (d[i].length > max)? d[i].length: max;
        }
        cols = max;
        data = new double[rows*cols];
        for(int i =0; i< d.length; ++i){
            System.arraycopy(d[i], 0, data, i * max, d[i].length);
        }
    }

    double[][] asArray(){
        double[][] twoD = new double[rows][cols];
        for(int i=0; i<rows; ++i){
            if (cols >= 0) System.arraycopy(data, i * cols, twoD[i], 0, cols);
        }

        return twoD;
    }

    double get(int r,int c) throws ArrayIndexOutOfBoundsException{
        if (r >= rows || c >= cols) throw new ArrayIndexOutOfBoundsException("To big rows or cols:" + "\nRows: " + rows + "\nCols: " + cols);
        return data[r * cols + c];
    }
    void set (int r,int c, double value)throws ArrayIndexOutOfBoundsException{
        if (r >= rows || c >= cols) throw new ArrayIndexOutOfBoundsException("To big rows or cols:" + "\nRows: " + rows + "\nCols: " + cols);
        data[r * cols + c] = value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");

        for (int i = 0; i < rows; ++i) {
            buf.append("[");

            for (int j = 0; j < cols; ++j) {
                if(j != 0) buf.append(", ");
                buf.append(Double.toString(data[i * cols + j]));
            }
            buf.append("]");
            if( i!= rows -1) buf.append(",\n");
        }
        buf.append("]");
        return buf.toString();
    }

    Matrix reshape(int newRows,int newCols) throws Exception {
        if (rows * cols != newRows * newCols)
            throw new Exception(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));

        rows = newRows;
        cols = newCols;

        return this;
    }

    int[] shape(){
        return new int[]{rows, cols};
    }

    Matrix add(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new ArrayIndexOutOfBoundsException("Matrices are not in the same shape");

        Matrix temp = new Matrix(rows, cols);
        for(int i=0; i< data.length; ++i){
            temp.data[i] = data[i] + m.data[i];
        }
        return temp;
    }
    Matrix sub(Matrix m) {
        return this.
                add(m.mul(-1));
    }
    Matrix mul(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new ArrayIndexOutOfBoundsException("Matrices are not in the same shape");

        Matrix temp = new Matrix(rows, cols);
        for(int i=0; i< data.length; ++i){
            temp.data[i] = data[i] * m.data[i];
        }
        return temp;
    }
    Matrix div(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new ArrayIndexOutOfBoundsException("Matrices are not in the same shape");

        Matrix temp = new Matrix(rows, cols);
        for(int i=0; i< data.length; ++i){
            temp.data[i] = data[i] / m.data[i];
        }
        return temp;
    }

    Matrix add(double w) {
        Matrix temp = new Matrix(rows, cols);
        for(int i=0; i< data.length; ++i){
            temp.data[i] = data[i] + w;
        }
        return temp;
    }
    Matrix sub(double w) {
        Matrix temp = new Matrix(rows, cols);
        for(int i=0; i< data.length; ++i){
            temp.data[i] = data[i] - w;
        }
        return temp;
    }
    Matrix mul(double w) {Matrix temp = new Matrix(rows, cols);
        for(int i=0; i< data.length; ++i){
            temp.data[i] = data[i] * w;
        }
        return temp;}
    Matrix div(double w) {Matrix temp = new Matrix(rows, cols);
        for(int i=0; i< data.length; ++i){
            temp.data[i] = data[i] / w;
        }
        return temp;}

    Matrix dot(Matrix m) throws ArrayIndexOutOfBoundsException{
        if(cols != m.rows) throw new ArrayIndexOutOfBoundsException("Not compatible dimensions.");

        double sum;
        Matrix tmp = new Matrix(rows, m.cols);

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < m.cols; ++j) {
                sum = 0;
                for (int k = 0; k < cols; ++k) {
                    sum += get(i, k) * m.get(k, j);
                }
                tmp.set(i, j, sum);
            }
        }
        return tmp;
    }

    double frobenius(){
        double temp = 0;

        for (double d : data) {
            temp += d * d;
        }
        return Math.sqrt(temp);
    }

}
