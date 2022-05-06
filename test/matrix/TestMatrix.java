package matrix;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TestMatrix {
    @Test
    void testConstructor() {
        Matrix<Object> matrix = new Matrix<>(1, 1);
    }
    @Test
    void testInsert() {
        Matrix<Object> matrix = new Matrix<>(2, 2);
        Object topLeft = new Object();
        Object topRight = new Object();
        Object bottomLeft = new Object();
        Object bottomRight = new Object();

        matrix.insert(0, 0, topLeft);
        assertEquals(topLeft, matrix.get(0, 0));

        matrix.insert(1, 0, bottomLeft);
        assertEquals(bottomLeft, matrix.get(1, 0));
        assertEquals(topLeft, matrix.get(0, 0));

        matrix.insert(0, 1, topRight);
        matrix.insert(1, 1, bottomRight);
        assertEquals(topRight, matrix.get(0, 1));
        assertEquals(bottomRight, matrix.get(1, 1));
        assertEquals(topLeft, matrix.get(0, 0));
    }
    @Test
    void testSize() {
        Matrix<Object> matrix3x3 = new Matrix<>(3, 3);
        assertEquals(3 * 3, matrix3x3.size());
        Matrix<Object> matrix4x3 = new Matrix<>(4, 3);
        assertEquals(4 * 3, matrix4x3.size());
        Matrix<Object> matrix2x5 = new Matrix<>(2, 5);
        assertEquals(2 * 5, matrix2x5.size());
    }
    @Test
    void testToString() {
        String matString;
        Matrix<Character> matrix1x1 = new Matrix<>(1, 1);
        matrix1x1.insert(0, 0, 'X');
        matString = "X";
        assertEquals(matString, matrix1x1.toString());
        Matrix<Character> matrix2x3 = new Matrix<>(2, 3);
        matrix2x3.insert(0, 0, 'A');
        matrix2x3.insert(0, 1, 'B');
        matrix2x3.insert(0, 2, 'C');
        matrix2x3.insert(1, 0, 'D');
        matrix2x3.insert(1, 1, 'E');
        matrix2x3.insert(1, 2, 'F');
        matString = "A\tB\tC\nD\tE\tF";
        assertEquals(matString, matrix2x3.toString());
        Matrix<Character> matrix1x2 = new Matrix<>(1, 2);
        matrix1x2.insert(0, 0, 'G');
        matrix1x2.insert(0, 1, 'H');
        matString = "G\tH";
        assertEquals(matString, matrix1x2.toString());
    }
    @Test
    void testIterator()
    {
        Matrix<Object> matrix = new Matrix<>(2, 2);
        Object topLeft = new Object();
        Object topRight = new Object();
        Object bottomLeft = new Object();
        Object bottomRight = new Object();

        matrix.insert(0, 0, topLeft);
        matrix.insert(0, 1, topRight);
        matrix.insert(1, 0, bottomLeft);
        matrix.insert(1, 1, bottomRight);

        Iterator<Object> iterator = matrix.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(topLeft, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(bottomLeft, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(topRight, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(bottomRight, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
