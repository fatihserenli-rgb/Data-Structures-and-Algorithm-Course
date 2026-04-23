import java.util.ArrayList;

/**
 * A min-heap based implementation of the MyPriorityQueue interface.
 *
 * @param <T> the type of elements maintained by this heap; must implement Comparable
 */
public class MinHeap<T extends Comparable<T>> implements MyPriorityQueue<T> {
    /**
     * Internal list to store heap elements.
     * @uml.property name="heap"
     */
    private ArrayList<T> heap;

    /**
     * Constructor
     * Initializes an empty min-heap.
     */
    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Adds an item to the heap and maintains the heap property.
     *
     * @param item the item to be added
     * @see MyPriorityQueue#add(Object)
     */
    @Override
    public void add(T item) {
        heap.add(item);
        toUp(heap.size() - 1);
    }

    /**
     * Removes and returns the smallest element from the heap.
     * If the heap is empty, returns null.
     *
     * @return the smallest element in the heap, or null if the heap is empty
     * @see MyPriorityQueue#poll()
     */
    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T root = heap.get(0);
        T lastItem = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, lastItem);
            toDown(0);
        }

        return root;
    }

    /**
     * Checks whether the heap is empty.
     *
     * @return true if the heap is empty; false otherwise
     * @see MyPriorityQueue#isEmpty()
     */
    @Override
    public Boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Moves the element at the specified index up to restore heap order.
     *
     * @param index the index of the element to move up
     */
    private void toUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * Moves the element at the specified index down to restore heap order.
     *
     * @param index the index of the element to move down
     */
    private void toDown(int index) {
        int leftChild, rightChild, minChild;

        while (true) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minChild = index;

            if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(minChild)) < 0) {
                minChild = leftChild;
            }

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(minChild)) < 0) {
                minChild = rightChild;
            }

            if (minChild == index) {
                break;
            }

            swap(index, minChild);
            index = minChild;
        }
    }

    /**
     * Swaps the elements at the two specified positions in the heap.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

