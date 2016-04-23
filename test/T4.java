/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitriy.mamishev
 */


public class T4 {

    public static int getClosestToZero(int[] a) {
        /*
          Please implement this method to
          return the number in the array that is closest to zero.
          If there are two equally close to zero elements like 2 and -2
          - consider the positive element to be "closer" to zero.
         */
        //int[] ar = {1, -2, -5, -6, -9, -5};
        int almostZero = Integer.MAX_VALUE;
        for (Integer current : a) {
            if (Math.abs(current) < Math.abs(almostZero)) {
                almostZero = current;
            } else if (Math.abs(current) == Math.abs(almostZero)) {
                almostZero = current > almostZero ? current : almostZero;
            }
        }
        return almostZero;
    }

    public static void main(String[] args) {

        int[] a = {8, -2, -5, -6, -9, -5};
        System.out.println(T4.getClosestToZero(a));

    }

}
