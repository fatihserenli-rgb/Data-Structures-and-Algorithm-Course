/**
 * A generic priority queue interface where elements are ordered according to their natural ordering.
 *
 * @param <T> the type of elements in the queue; must implement Comparable interface
 */
public interface MyPriorityQueue <T extends Comparable<T>> {

    /**
     * Adds an element to the priority queue.
     *
     * @param t the element to be added
     */
    void add(T t);

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue, or null if the queue is empty
     */
    T poll();

    /**
     * Checks whether the priority queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    Boolean isEmpty();
}

