package Recursion.patterns;

import static sorting.SortingAlgo.swap;

public class Patterns {

    public static void triangle(){
            trianglePrinter(4,0);
    }

    private static void trianglePrinter(int r, int c){
        if(r==0) return;
        if(c < r){
            trianglePrinter(r,c+1);
            System.out.print("*");
        }else{
            trianglePrinter(r-1,0);
            System.out.println();
        }
    }

    static  int maxIndex = 0;
    public static void selection(int[] nums, int r, int c){
        if(r == 0) return;
        if(c < r){
            if(nums[c] > nums[maxIndex]){
                maxIndex = c;
            }
            selection(nums,r,c+1);
        }else{
            swap(nums,r-1,maxIndex);
            maxIndex = 0;
            selection(nums,r-1,0);
        }
    }


    public static String removeA(String input){
        if(input.isEmpty()) return "";

        var ch = input.charAt(0);
        if(ch == 'a'){
            return removeA(input.substring(1));
        }else{
            return ch + removeA(input.substring(1));
        }
    }

}
