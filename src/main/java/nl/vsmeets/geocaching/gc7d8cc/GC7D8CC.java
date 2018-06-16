/**
 *
 */
package nl.vsmeets.geocaching.gc7d8cc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

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
	 * The logger for this class.
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Fill the board with the initial values.
	 *
	 * @param board
	 *            The board to fill.
	 */
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
		board.put(1, 2, null);
		board.put(1, 3, null);
		board.put(1, 4, 1);
		board.put(1, 5, null);
		board.put(1, 6, 3);
		board.put(1, 7, null);
		board.put(1, 8, 7);
		board.put(1, 9, null);
		board.put(1, 10, null);

		board.put(2, 1, null);
		board.put(2, 2, 3);
		board.put(2, 3, null);
		board.put(2, 4, null);
		board.put(2, 5, null);
		board.put(2, 6, 5);
		board.put(2, 7, 4);
		board.put(2, 8, null);
		board.put(2, 9, 6);
		board.put(2, 10, null);

		board.put(3, 1, 6);
		board.put(3, 2, null);
		board.put(3, 3, null);
		board.put(3, 4, 4);
		board.put(3, 5, 2);
		board.put(3, 6, null);
		board.put(3, 7, null);
		board.put(3, 8, null);
		board.put(3, 9, null);
		board.put(3, 10, null);

		board.put(4, 1, 0);
		board.put(4, 2, null);
		board.put(4, 3, null);
		board.put(4, 4, null);
		board.put(4, 5, 5);
		board.put(4, 6, 1);
		board.put(4, 7, 3);
		board.put(4, 8, null);
		board.put(4, 9, null);
		board.put(4, 10, 6);

		board.put(5, 1, null);
		board.put(5, 2, 3);
		board.put(5, 3, 0);
		board.put(5, 4, null);
		board.put(5, 5, 4);
		board.put(5, 6, 8);
		board.put(5, 7, null);
		board.put(5, 8, null);
		board.put(5, 9, 5);
		board.put(5, 10, null);

		board.put(6, 1, 0);
		board.put(6, 2, 2);
		board.put(6, 3, 8);
		board.put(6, 4, null);
		board.put(6, 5, 9);
		board.put(6, 6, null);
		board.put(6, 7, null);
		board.put(6, 8, null);
		board.put(6, 9, 3);
		board.put(6, 10, null);
	}

	/**
	 * Find the first cell that contains no value.
	 *
	 * @param board
	 *            The board to check.
	 * @return Optional the cell with no value.
	 */
	private Optional<Cell<Integer, Integer, Integer>> findFirstEmptyCell(final Table<Integer, Integer, Integer> board) {
		return board.cellSet().stream().filter(c -> c.getValue() == null).findFirst();
	}

	/**
	 * Get the neighbor values of a cell in a row.
	 *
	 * @param row
	 *            The complete row.
	 * @param column
	 *            The number of the column.
	 * @return A set with all the values that are neighbors of the cell in the
	 *         column.
	 */
	private Collection<? extends Integer> getNeighborValues(final Map<Integer, Integer> row, final Integer column) {
		final Set<Integer> values = new HashSet<>(9);
		if (column > 1) {
			values.add(row.get(column - 1));
		}
		values.add(row.get(column));
		if (column < 10) {
			values.add(row.get(column + 1));
		}
		return values;
	}

	/**
	 * Get the neighbor values of a cell on the board.
	 *
	 * @param board
	 *            The board.
	 * @param row
	 *            The row number of the cell.
	 * @param column
	 *            The column number of the cell.
	 * @return A set with all the values that are neighbors of the cell pn the
	 *         board.
	 */
	private Set<Integer> getNeighborValues(final Table<Integer, Integer, Integer> board, final Integer row,
			final Integer column) {
		final Set<Integer> values = new HashSet<>(9);
		if (row > 1) {
			values.addAll(getNeighborValues(board.row(row - 1), column));
		}
		values.addAll(getNeighborValues(board.row(row), column));
		if (row < 9) {
			values.addAll(getNeighborValues(board.row(row + 1), column));
		}
		return values;
	}

	/**
	 * Get all the possible values for a cell.
	 *
	 * @param board
	 *            The board.
	 * @param row
	 *            The row of the cell to solve.
	 * @param column
	 *            The column of the cell to solve.
	 * @return A {@code Set} with all the possible values for the cell. This
	 *         {@code Set} can be empty.
	 */
	private Set<Integer> getPossibleValues(final Table<Integer, Integer, Integer> board, final Integer row,
			final Integer column) {
		logger.log(Level.FINE, "getPossibleValues: row={1} column={2} board:{0}",
				new Object[] { toString(board), row, column });
		final Set<Integer> possibleValues = new HashSet<>(10);

		// Add all possible values.
		IntStream.rangeClosed(0, 9).forEach(v -> possibleValues.add(v));
		logger.log(Level.FINE, "possibleValues={0}", possibleValues);

		// Remove duplicates in one row.
		board.row(row).values().stream().filter(v -> v != null).forEach(v -> possibleValues.remove(v));
		logger.log(Level.FINE, "possibleValues={0}", possibleValues);

		// The sum of a column must match that of the value in row-0.
		final Integer sum0 = board.get(0, column);
		final Integer sum1To6 = board.column(column).entrySet().stream().filter(e -> e.getKey() != 0)
				.map(e -> e.getValue()).filter(v -> v != null).reduce(0, Integer::sum);
		logger.log(Level.FINE, "sum1To6={0}", sum1To6);
		IntStream.rangeClosed(sum0 - sum1To6 + 1, 9).forEach(v -> possibleValues.remove(v));
		logger.log(Level.FINE, "possibleValues={0}", possibleValues);

		// The cell can't have the same value as a neighbor.
		final Set<Integer> neighborValues = getNeighborValues(board, row, column);
		possibleValues.removeAll(neighborValues);

		return possibleValues;
	}

	/**
	 * Solve the board.
	 *
	 * @param board
	 *            The board to solve.
	 * @return The solved board or {@code null} in case the board isn't solvable.
	 */
	private Table<Integer, Integer, Integer> solve(final Table<Integer, Integer, Integer> board) {
		final Optional<Cell<Integer, Integer, Integer>> optionalCell = findFirstEmptyCell(board);
		if (optionalCell.isPresent()) {
			final Integer row = optionalCell.get().getRowKey();
			final Integer column = optionalCell.get().getColumnKey();
			return solve(board, row, column);
		} else {
			return board;
		}
	}

	/**
	 * Solve the board.
	 *
	 * @param board
	 *            The board to solve.
	 * @param row
	 *            The row of the cell to solve.
	 * @param column
	 *            The column of the cell to solve.
	 * @return The solved board or {@code null} in case the board isn't solvable.
	 */
	private Table<Integer, Integer, Integer> solve(final Table<Integer, Integer, Integer> board, final Integer row,
			final Integer column) {
		final Set<Integer> possibleValues = getPossibleValues(board, row, column);
		for (final Integer value : possibleValues) {
			final Table<Integer, Integer, Integer> nextBoard = ArrayTable.create(board);
			nextBoard.put(row, column, value);
			final Table<Integer, Integer, Integer> solvedBoard = solve(nextBoard);
			if (solvedBoard != null) {
				return solvedBoard;
			}
		}
		return null;
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
		final Table<Integer, Integer, Integer> solvedBoard = solve(board);
		if (solvedBoard == null) {
			logger.log(Level.INFO, "This board isn't solvable!");
		} else {
			logger.log(Level.INFO, "The board is solved as: {0}", toString(solvedBoard));
		}
	}

	/**
	 * Convert a table to a string. Ecery row will be on a separate line.
	 *
	 * @param table
	 *            The table to show.
	 * @return The table as a string.
	 */
	private String toString(final Table<Integer, Integer, Integer> table) {
		final StringBuilder result = new StringBuilder();
		final Map<Integer, Map<Integer, Integer>> rowMap = table.rowMap();
		result.append(System.lineSeparator());
		for (final Entry<Integer, Map<Integer, Integer>> row : rowMap.entrySet()) {
			result.append(row.getKey()).append("=").append(row.getValue()).append(System.lineSeparator());
		}
		return result.toString();
	}
}
