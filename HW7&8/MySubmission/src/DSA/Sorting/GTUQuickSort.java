package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * A sorter that implements quick sort algorithm.
 * Uses insertion sort for small partitions if another sorter is provided.
 */
public class GTUQuickSort extends GTUSorter {

    private final GTUSorter fallbackSorter;
    private final int threshold;
    private final Random rand;

    /**
     * Constructor with threshold and fallback sorter.
     *
     * @param threshold      partition size below which fallback sorter is used
     * @param fallbackSorter alternative sorter for small partitions
     */
    public GTUQuickSort(GTUSorter fallbackSorter,int threshold) {
        if (threshold <= 0 || fallbackSorter == null) 
            throw new IllegalArgumentException("Invalid threshold or fallback sorter!");
        
        this.threshold = threshold;
        this.fallbackSorter = fallbackSorter;
        this.rand = new Random();
    }

    /**
     * Default constructor with threshold=10 and InsertSort as fallback.
     */
    public GTUQuickSort() {
        this(new GTUInsertSort(),10);
    }

    /**
     * Sorts the subarray using quick sort.
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        if (end - start <= 1) return;

        if (fallbackSorter != null && (end - start) <= threshold) {
            fallbackSorter.sort(arr, start, end, comparator);
            return;
        }

        int pivotIndex = partition(arr, start, end, comparator);

        sort(arr, start, pivotIndex, comparator);
        sort(arr, pivotIndex + 1, end, comparator);
    }

    private <T> int partition(T[] arr, int start, int end, Comparator<T> comparator) {
        int pivotIndex = start + rand.nextInt(end - start);
        T pivot = arr[pivotIndex];
        
        swap(arr, pivotIndex, end - 1);
        
        int i = start;
        for (int j = start; j < end - 1; j++) {
            if (comparator.compare(arr[j], pivot) < 0) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, end - 1);
        return i;
    }

    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}