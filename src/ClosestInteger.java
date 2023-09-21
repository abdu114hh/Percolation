import stdlib.StdIn;

import java.util.Arrays;

public class ClosestInteger {

    public static void main(String[] args){
        int[] a = StdIn.readAllInts();
        closestPair(a);


    }

    public static void closestPair(int[] a){
        Arrays.sort(a);
        int[] difference = new int[a.length];
        int x = 0;

        for (int i = a.length - 1; i >= 0 ; i++) {
          x  = a[i] - a[i-1];


        }




    }
}
