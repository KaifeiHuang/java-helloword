package com.kaifei.algorithm;

import org.junit.Test;

public class SortAlgo {

    /**
     * 算法步骤
     * 1.从第二个元素（第一个要被排序的新元素）开始， 从后向前扫描之前元素
     * 2.如果当前扫描元素 > 新元素，将扫描元素移动到下一位
     * 3.重复步骤2，直到找到一个小于或者等于新元素的位置
     * 4. 将新元素插入到该位置
     * 5. 对于之后的元素重复步骤1~4
     *
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * 「时间复杂度」在此算法中就是计算比较的次数，
     * 第一个元素我们需要比较1次，第二个元素2次，对于第n个元素，我们需要和之前的元素比较n次，比较总数量也就是 1 + 2 + … + n = n(n + 1) / 2
     * ≈ n^2。因为我们调换位置时采用「原地操作」(in place)，所以不需要额外空间，既空间复杂度为O(1)。
     * @param arr
     */
    void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int insertIndex = i-1;
            int cur = arr[i];
            while (insertIndex>=0 && arr[insertIndex] > cur) {
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1] = cur;
        }
    }

    /**
     * 对于当前的数组，取最后一个元素当做基准数（pivot）
     * 将所有比基准数小的元素排到基准数之前，比基准数大的排在基准数之后
     * 当基准数被放到准确的位置之后，根据基数数的位置将元素切分为前后两个子数组
     * 对子数组采用步骤1~4的递归操作，直到子数组的长度小于等于1为止
     * @param arr
     */
    void quickSort(int[] arr){

    }

    private int partitionArr(int[] arr, int left, int right) {
        int pivot = arr[right];
        int lIndex = left;
        int rIndex = right-1;

        while (true) {
            while (lIndex < rIndex && arr[lIndex] <= pivot) {
                lIndex++;
            }

            while (lIndex < rIndex && arr[rIndex] >= pivot) {
                rIndex--;
            }

            if (lIndex > rIndex) {
                break;
            }

            swap(arr, lIndex, rIndex);
        }

        swap(arr, lIndex, rIndex);
        return lIndex;

    }

    private void swap(int[] arr, int lIndex, int rIndex) {
        int tmp = arr[lIndex];
        arr[lIndex] = arr[rIndex];
        arr[rIndex] = tmp;

    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    int arr[] = {10,7,3,67,6};

    @Test
    public void testInsertSort() {
        insertSort(arr);
        printArray(arr);
    }
}
