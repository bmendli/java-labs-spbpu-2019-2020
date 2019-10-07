package lab1.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatrixTest {
    @Test
    void testThrowExceptions() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Matrix(-1, 3)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Matrix(3, 0)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new Matrix(-5, -3)
        );
    }

    @Test
    void testMatrix() {
        int countOne = 0, countZero = 0;
        Matrix matrix = new Matrix(5, 8);
        for (int i = 0; i < matrix.getLines(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                if (matrix.getMatrix()[i][j] == 0) {
                    countZero++;
                } else if (matrix.getMatrix()[i][j] == 1) {
                    countOne++;
                }
            }
        }
        assertEquals(
            countOne,
            matrix.getLines()
        );
        assertEquals(
            countZero,
            matrix.getLines() * matrix.getColumns() - matrix.getLines()
        );
    }
}
