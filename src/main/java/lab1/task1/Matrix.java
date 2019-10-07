package lab1.task1;

public class Matrix {
    /*
    Matrix with size lines * columns
    1 0 0 ... 0
    1 0 0 ... 0
    ...
    1 0 0 ... 0
    */
    private int[][] matrix;
    private int lines;
    private int columns;

    public Matrix(int lines, int columns) {
        if (lines <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Incorrect lines and columns");
        }
        matrix = new int[lines][columns];
        this.lines = lines;
        this.columns = columns;
        for (int i = 0; i < lines; i++) {
            matrix[i][0] = 1;
            for (int j = 1; j < columns; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
