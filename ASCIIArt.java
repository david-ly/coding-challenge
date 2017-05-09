import java.util.ArrayList;

public class ASCIIArt {
    private int _row;
    private int _col;
    private ArrayList<ArrayList<Character>> _map;

    public static void main(String[] args) {
        ASCIIArt art = new ASCIIArt(10, 7);
        art.insertRow(5, '|');
        art.insertCol(8, '/');
        art.insertCol(5, 'a');
        art.insertRow(12, 'b');
        art.setChar(5, 5, 'A');
        art.dump();
    }

    /**
     * For the creation of an initial picture, I imagined that the instance would be supplied with integers representing
     * the ROW x COL dimensions. Along with that, I made every character a '_' to represent blank space. For space com-
     * plexity, it would be bounded by O(n x m) where n is the number of rows and m is the number of columns. In terms
     * of time complexity, it would be bounded by O(n + m) as there are two for loops, the first one iterates m times
     * and the second loop iterates n times. The add functions inside these for loops are negligible as adding onto the
     * end of an ArrayList takes O(1) constant time.
     */
    ASCIIArt(int row, int col) {
        _row = row;
        _col = col;
        _map = new ArrayList<>();
        ArrayList<Character> holders = new ArrayList<>();
        for (int j = 0; j < _col; j++) {
            holders.add('_');
        }
        for (int i = 0; i < _row; i++) {
            _map.add(new ArrayList<>(holders));
        }
    }

    /**
     * The insertion of a row onto the top, middle, or bottom is the main reason why I chose to use the ArrayList API.
     * Since it natively has an add method which allows the addition of an Object into a specified index, I thought it
     * would be the most intuitive to implement this dynamic ASCII array. This function is bounded by O(n + m) where n
     * is the amount elements in the new row and m is the amount of columns. The for loop iterates m times and the addi-
     * tion of the new row into the ASCII map is bounded by O(n) where n is the amount of elements in the new row. This
     * is because of the add(int index, Object) method used to add into the middle of the array as that method is bound-
     * ed by O(n) time. If insertRow exclusively added to the end, the run time would be O(m) as the add(Object) method
     * is an O(1) constant time operation.
     */

    public void insertRow(int row, char ch) {
        if (row - 1 <= _row) {
            ArrayList<Character> nrow = new ArrayList<>();
            for (int i = 0; i < _col; i++) {
                nrow.add(ch);
            }
            if (row - 1 == _row) {
                _map.add(nrow);
            } else {
                _map.add(row - 1, nrow);
            }
            _row += 1;
        }
    }

    /**
     * The run-time of this function is bounded by O(n x m) where n is amount of rows and m is the amount of columns.
     * The for loop iterates n times, however the operations happening inside the for loop is bounded by O(m) as if we
     * added a column to the middle of the array, we would use the add(int index, Object) method which is bounded by
     * O(m) time where m is the amount of elements in a row or the amount of columns in the ASCII array. Because this
     * method happens inside the for loop, these two bounds are multiplied to bound this function to O(n x m)
     */

    public void insertCol(int col, char ch) {
        if (col - 1 <= _col) {
            for (int i = 0; i < _row; i++) {
                if (col - 1 == _col) {
                    _map.get(i).add(ch);
                } else {
                    _map.get(i).add(col - 1, ch);
                }
            }
            _col += 1;
        }
    }

    /**
     * The runtime for this function is bounded by O(1). The function first gets the ROWth array and then sets the COLth
     * element of the array to the character CH. The get(int index) and set(int index, Object) methods are both bounded
     * by O(1) time and doing both of these operations keeps this function bounded by O(1) constant time.
     */

    public void setChar(int row, int col, char ch) {
        if (row - 1 <= _row && col - 1 <= _col) {
            _map.get(row - 1).set(col - 1, ch);
        }
    }


    public void dump() {
        for (int i = 0; i < _row; i++) {
            System.out.println(_map.get(i).toString());
        }
    }
}
