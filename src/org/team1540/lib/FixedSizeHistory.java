package org.team1540.lib;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple wrapper around a {@link LinkedList} that keeps the list at a fixed size and upon adding
 * an element that would put it over the max size, removes the first element.
 *
 * @param <T> The type of elements held in this history.
 *
 * @author Zachary Robinson
 */
public class FixedSizeHistory<T> {
  private List<T> history;
  private int maxSize;

  /**
   * Constructs a new {@link FixedSizeHistory} with the specified maximum size.
   *
   * @param maxSize The maximum size of the history.
   */
  public FixedSizeHistory(int maxSize) {
    this.maxSize = maxSize;
    history = new LinkedList<>();
  }

  /**
   * Adds an item to the history. If the history has reached its max size, remove the first
   * element.
   *
   * @param item The item to add.
   *
   * @return {@code true} if adding this item resulted in removing an item from the beginning of the
   * history.
   */
  public boolean add(T item) {
    boolean hitMax = false;

    if (history.size() >= maxSize) {
      history.remove(0);
      hitMax = true;
    }

    history.add(item);
    return hitMax;
  }

  /**
   * Returns the element at the specified position in the history, corresponding to when it was
   * placed. For example, the last element placed will be at an index of <code>{@link #size()} -
   * 1.</code>
   *
   * @param index The element index to return
   *
   * @return tThe element at the specified position in the history
   *
   * @throws IndexOutOfBoundsException if the index is out of range <code>(index < 0 || index >=
   * {@link #size()})</code>
   */
  public T get(int index) {
    return history.get(index);
  }

  /**
   * Returns the number of elements in the history. This can, but does not have to be, the maximum
   * number of elements allowed in the history.
   *
   * @return the number of elements currently in the history.
   */
  public int size() {
    return history.size();
  }

  /**
   * Returns the contents of the history as an array. This method is a thin wrapper around {@link
   * List#toArray(T[])} and as such is in accordance with that documentation.
   *
   * <p> Providing an already-created array as an argument allows reusing the same array across
   * update cycles and avoids the performance overhead of constantly allocating/deallocating new
   * arrays.
   *
   * @param array The array to fill.
   *
   * @return An array containing the elements of the history.
   *
   * @throws NullPointerException if {@code array} is null
   */
  public T[] getHistoryAsArray(T[] array) {
    return history.toArray(array);
  }
}
