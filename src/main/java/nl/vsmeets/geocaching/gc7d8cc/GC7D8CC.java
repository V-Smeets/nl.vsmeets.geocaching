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

	/**
	 * Start solving the mystery.
	 */
	private void start() {
		// Row 0 contains the sum values!
		// Row 1-6 shall contain the unique values 0-9.
		// For every column, the sum of the rows 1-6 shall be equal to row 0.
		final Table<Integer, Integer, Integer> board = ArrayTable.create(new Range(0, 6), new Range(1, 10));
	}

}
