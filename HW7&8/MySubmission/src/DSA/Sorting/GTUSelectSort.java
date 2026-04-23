package DSA.Sorting;

import java.util.Comparator;

/**
 * A sorter that implements selection sort algorithm.
 */
public class GTUSelectSort extends GTUSorter {

    /**
     * Sorts the subarray using selection sort.
     *
     * @param <T>        the type of elements
     * @param arr        the array to be sorted
     * @param start      starting index (inclusive)
     * @param end        ending index (exclusive)
     * @param comparator comparator for comparing elements
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        for (int i = start; i < end - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < end; j++) {
                if (comparator.compare(arr[j], arr[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            T tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
    }
}