package matrix;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A generic 2D-matrix.
 * @param <E> the cell type.
 */
public class Matrix<E> implements Iterable<E> {
    private ArrayList<ArrayList<E>> myList;

    public static void main(String[] args) {
        Matrix<String> m = new Matrix<String>(2, 2);

        m.insert(0, 0, "a");
        m.insert(0, 1, "b");
        m.insert(1, 0, "c");
        m.insert(1, 1, "d");

        System.out.println(m + "\n");

        for (String element : m) {
            System.out.println(element);
        }
    }

    /**
     * Constructs a Matrix.
     *
     * @param rows the number of rows.
     * @param columns the number of columns.
     */
    public Matrix(int rows, int columns) {
        myList = new ArrayList<ArrayList<E>>();

        for (int i = 0; i < rows; i++) {
            myList.add(new ArrayList<E>());
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                myList.get(i).add(null);
            }
        }
    }

    /**
     * Assigns a value to a given cell, specified by its row, column coordinates.
     *
     * @param row the row index with 0-based indexing.
     * @param column the column index with 0-based indexing.
     * @param value the value to be assigned to the given cell.
     */
    public void insert(int row, int column, E value) {
        myList.get(row).set(column, value);
    }

    /**
     * Gets the value at a given cell, specified by its row, column coordinates.
     *
     * @param row the row index with 0-based indexing.
     * @param column the column index with 0-based indexing.
     * @return the value located at the given cell.
     */
    public E get(int row, int column) {
        return myList.get(row).get(column);
    }

    /**
     * Gets the total number of cells in the matrix.
     *
     * @return an int equal to the total number of cells in the matrix.
     */
    public int size() {
        return myList.size() * myList.get(0).size();
    }

    /**
     * Converts the matrix to String format.
     *
     * @return a String representation of the matrix.
     */
//    @Override
    public String toString() {
        int rows = myList.size();
        int columns = myList.get(0).size();

        String string = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                string += this.get(i, j);
                if (j != columns - 1) {
                    string += "\t";
                }

            }
            if (i != rows - 1) {
                string += "\n";
            }
        }
        return string;
    }

    /**
     * Gets an iterator for the matrix. The iterator follows column-major order.
     *
     * @return an iterator for the matrix.
     */
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            final int rows = myList.size();
            final int cols = myList.get(0).size();

            int row = 0;
            int col = 0;

            public boolean hasNext() {
                return col < cols;
            }

            public E next() {
                E result = myList.get(row++).get(col);
                if (row == rows) {
                    row = 0;
                    col++;
                }
                return result;
            }
        };
    }

}