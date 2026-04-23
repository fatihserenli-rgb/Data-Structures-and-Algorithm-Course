package DSA.Sorting;

import java.util.Comparator;

/**
 * A sorter that implements insertion sort algorithm.
 */
public class GTUInsertSort extends GTUSorter {

    /**
     * Sorts the subarray using insertion sort.
     *
     * @param <T>        the type of elements
     * @param arr        the array to be sorted
     * @param start      starting index (inclusive)
     * @param end        ending index (exclusive)
     * @param comparator comparator for comparing elements
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        for (int i = start + 1; i < end; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= start && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
}
