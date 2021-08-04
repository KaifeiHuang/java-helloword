package com.kaifei.algorithm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    /**
     * TimeSpace： O(n)
     * MeomerySpace: O(n)
     *
     * @param array
     * @param target
     * @return
     */
    public static int[] getTargetIndexes(int[] array, int target) {

        Map<Integer, Integer> indexesMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            if (indexesMap.containsKey(target - array[i])){
                return new int[]{indexesMap.get(target-array[i]), i};
            }

            indexesMap.put(array[i], i);
        }

        return new int[2];

    }

    public static int[] getTargetIndexesWithTwoPointer(int[] array, int target) {

        int left = 0;
        int right = array.length - 1;


        for (int i = 0; i < array.length; i++) {
            int val = array[left] + array[right];
            if ( val == target) {
                return new int[]{left, right};
            }else if (val > target){
                right--;
            }else {
                left++;
            }
        }

        return new int[2];

    }

    @Test
    public void  testGetTargetIndex(){
        int[] array = {2, 9, 11, 15, 7};
        int target = 9;
        int[] resArr = getTargetIndexes(array, target);
        System.out.println(resArr);
        for (int i = 0; i < resArr.length; i++) {
            System.out.println(resArr[i]);
        }
    }

    @Test
    public void  testGetTargetIndexWithTwoPointer(){
        int[] array = {2, 9, 11, 15, 7};
        int target = 9;
        int[] resArr = getTargetIndexesWithTwoPointer(array, target);
        for (int i = 0; i < resArr.length; i++) {
            System.out.println(resArr[i]);
        }
    }
}
