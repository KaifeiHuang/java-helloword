package com.kaifei.algorithm;

import org.junit.Test;

public class KMP {

    boolean bfSearch(String str, String pat){
        int j=0;
        int count = 0;
        for (int i=0; i< str.length(); i++) {
            char ch = str.charAt(i);
            if (j < pat.length() && ch != pat.charAt(j)){
                j=0;
            }



            if (j == pat.length() -1) {
                j=0;
                count ++;
               continue;
            }

            j++;
        }
        System.out.println("count = " + count);

        if (count > 0) {
            return true;
        }

        return false;
    }

    @Test
    public void testBF(){
        String str = "deswgjxpriwujfs pppppp  yuyfdushfjshf  priwujfs";
        String pat = "priwujfs";
        boolean bfSearch = bfSearch(str, pat);
        System.out.println(bfSearch);
    }

    @Test
    public void testKMP(){

    }
}
