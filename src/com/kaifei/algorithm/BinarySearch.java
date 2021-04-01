package com.kaifei.algorithm;

import org.junit.Test;

/**
 * 二分法算法要求： 数组必须时有序的
 * 
 * <p>时间复杂度： O(log2N)</p>
 * 最糟糕的情况下，我们需要将数组迭代切分到只有一个元素，那么需要多少次才会切到只有一个元素呢？
 * 假设如果数组有n个元素，切分的次数为k，每次都切一半，也就是 n / (2^k) = 1，转换公式为 2^k = n，那么k就是log2N，
 * 所以时间复杂度为O(log2N)。
 *
 * <p>空间复杂度： O(1)</p>
 * 因为我们不需要额外的空间，所以空间复杂度为O(1)。
 */
public class BinarySearch {

    /**
     * search the target element
     *
     * @param arr int array
     * @param left left index
     * @param right right index
     * @param target the desired element
     * @return the index of the desired element
     */
    public static int search(int[] arr, int left, int right, int target){

        while (left <=right){
            int mid = (left + right)/2;
            if (arr[mid] == target){
                return mid;
            }else if(target < arr[mid]){
                right = mid -1;
            }else {
                right = mid + 1;
            }
        }

        return -1;
    }

    @Test
    public void testSearch(){
        int[] arr = {1,2,3,4,5,6};
        int search = BinarySearch.search(arr, 0, arr.length - 1, 3);
        System.out.println("search result: " + search);
    }
}
