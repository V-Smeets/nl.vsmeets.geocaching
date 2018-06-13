/**
 *
 */
package nl.vsmeets.geocaching.gc7d8cc;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

/**
 * Solve the mystery GC7D8CC.
 *
 * @author vincent
 */
public class GC7D8CC {

	/**
	 * The main entry point.
	 *
	 * @param args
	 *            The command line arguments.
	 */
	public static void main(final String[] args) {
		final GC7D8CC main = new GC7D8CC();
		main.start();
	}

	private void fill(final Table<Integer, Integer, Integer> board) {
		board.put(0, 1, 24);
		board.put(0, 2, 24);
		board.put(0, 3, 32);
		board.put(0, 4, 28);
		board.put(0, 5, 29);
		board.put(0, 6, 29);
		board.put(0, 7, 33);
		board.put(0, 8, 29);
		board.put(0, 9, 25);
		board.put(0, 10, 17);

		board.put(1, 1, 8);
		// board.put(1, 2, );
		// board.put(1, 3, );
		board.put(1, 4, 1);
		// board.put(1, 5, );
		board.put(1, 6, 3);
		// board.put(1, 7, );
		board.put(1, 8, 7);
		// board.put(1, 9, );
		// board.put(1, 10, );

		// board.put(2, 1, );
		board.put(2, 2, 3);
		// board.put(2, 3, );
		// board.put(2, 4, );
		// board.put(2, 5, );
		board.put(2, 6, 5);
		board.put(2, 7, 4);
		// board.put(2, 8, );
		board.put(2, 9, 6);
		// board.put(2, 10, );

		board.put(3, 1, 6);
		// board.put(3, 2, );
		// board.put(3, 3, );
		board.put(3, 4, 4);
		board.put(3, 5, 2);
		// board.put(3, 6, );
		// board.put(3, 7, );
		// board.put(3, 8, );
		// board.put(3, 9, );
		// board.put(3, 10, );

		board.put(4, 1, 0);
		// board.put(4, 2, );
		// board.put(4, 3, );
		// board.put(4, 4, );
		board.put(4, 5, 5);
		board.put(4, 6, 1);
		board.put(4, 7, 3);
		// board.put(4, 8, );
		// board.put(4, 9, );
		board.put(4, 10, 6);

		// board.put(5, 1, );
		board.put(5, 2, 3);
		board.put(5, 3, 0);
		// board.put(5, 4, );
		board.put(5, 5, 4);
		board.put(5, 6, 8);
		// board.put(5, 7, );
		// board.put(5, 8, );
		board.put(5, 9, 5);
		// board.put(5, 10, );

		board.put(6, 1, 0);
		board.put(6, 2, 2);
		board.put(6, 3, 8);
		// board.put(6, 4, );
		board.put(6, 5, 9);
		// board.put(6, 6, );
		// board.put(6, 7, );
		// board.put(6, 8, );
		board.put(6, 9, 3);
		// board.put(6, 10, );
	}

	/**
	 * Start solving the mystery.
	 */
	private void start() {
		// Row 0 contains the sum values!
		// Row 1-6 shall contain the unique values 0-9.
		// For every column, the sum of the rows 1-6 shall be equal to row 0.
		final Table<Integer, Integer, Integer> board = ArrayTable.create(new Range(0, 6), new Range(1, 10));
		fill(board);
	}
}
