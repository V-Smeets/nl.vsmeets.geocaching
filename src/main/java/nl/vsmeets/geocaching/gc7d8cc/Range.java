/**
 *
 */
package nl.vsmeets.geocaching.gc7d8cc;

import java.util.Iterator;

/**
 * An Integer range as Iterable.
 *
 * @author vincent
 */
public class Range implements Iterable<Integer> {

	/**
	 * The implementation of the iterator.
	 *
	 * @author vincent
	 */
	private class RangeIterator implements Iterator<Integer> {

		/**
		 * The next value. Start with the first.
		 */
		private int value = firstValue;

		@Override
		public boolean hasNext() {
			return value <= lastValue;
		}

		@Override
		public Integer next() {
			return value++;
		}
	}

	/**
	 * The first available value. (inclusive)
	 */
	private final int firstValue;

	/**
	 * The last available value. (inclusive)
	 */
	private final int lastValue;

	/**
	 * Create a range with values between {@code firstValue} and {@code lastValue}.
	 * Both bounds are inclusive.
	 *
	 * @param firstValue
	 *            The first value.
	 * @param lastValue
	 *            The last value.
	 */
	public Range(final int firstValue, final int lastValue) {
		super();
		assert firstValue <= lastValue;
		this.firstValue = firstValue;
		this.lastValue = lastValue;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

}
