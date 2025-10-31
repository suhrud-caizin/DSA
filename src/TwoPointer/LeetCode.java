package TwoPointer;

import java.util.*;

public class LeetCode {
    public static int totalFruit(int[] fruits) {
        int l = 0, r = 0, max = Integer.MAX_VALUE;

        Map<Integer, Integer> map = new HashMap<>();

        while (r < fruits.length) {
            map.put(fruits[r], map.getOrDefault(fruits[r] + 1, 1));

            if (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l] - 1));
                if (map.get(fruits[l]) == 0) map.remove(fruits[l]);
                l++;
            }

            if (map.size() <= 2) {
                max = Math.max(max, r - l + 1);
            }
            r++;
        }

        return max;
    }

//    public int longestSubstring(String s, int k) {
//            char[] chars = s.toCharArray();
//            int l=0,r=0,max =0;
//            Map<Character,Integer> map = new HashMap<>();
//
//            while (r<chars.length){
//                map.put(chars[r],map.getOrDefault(chars[r],0)+1);
//                if(map.size() > k){
//                    map.put(chars[l],map.get(chars[l])-1);
//                    if(map.get(chars[l]) == 0) map.remove(chars[l]);
//                    l++;
//                }
//
//                if(map.size()<=k){
//                    max = Math.max(max, r-l+1);
//                }
//                r++;
//            }
//
//            return max;
//    }
    private boolean isMapValid(){
        Map<Character, Integer> map = new HashMap<>();
        map.put('a',1);

        for (var entry : map.values()){
                if(entry < 2) return false;
        }

        return  true;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int l = 0, r = 0;
        double[] ans = new double[nums.length - k + 1];
        int itr = 0;

        while(r < nums.length){
            if(r - l + 1 == k){
//                Queue<Integer> heap = new PriorityQueue<>();
                List<Integer> list = new ArrayList<>();
                for (int i = l; i <= r; i++) {
                    list.add(nums[i]);
                }

               list = list.stream().sorted().toList();

                Integer temp = list.get(k/2);
//                for (int i = 0; i <= (k - 1)/2; i++) {
//                    temp = heap.poll();
//                }

                if(k % 2 == 0){
                    ans[itr++] =  ((double)temp + list.get(k/2 - 1)) / 2;
                }else{
                    ans[itr++] = temp;
                }
                l++;
            }
            r++;
        }
        return ans;
    }


    Map<Character, Integer> map = new HashMap<>();

    // aaaaaaaaabbbcccccddddd
    public int longestSubstring(String s, int k) {
        char[] chars = s.toCharArray();
        int l = 0, r = 0, max = 0;

        char curr, prev = '_';
        int totalCount = 0;
        int cCount = 0;
        int pCount = 0;
        // "bbaaacbd" 3

        while (r < chars.length) {
            curr = chars[r];

            if (curr == prev) {
                cCount++;

                if(cCount >= k){
                    pCount = cCount;
                }
                // totalCount++;

            } else if (pCount >= k) {
                // totalCount += cCount;
                // cCount = 1;
                // totalCount++;
                // pCount = cCount;
                totalCount += pCount;
                cCount = 1;
                pCount = 0;
            } else {
                cCount = 1;
                pCount = 0;
                totalCount = 0;
            }

            map.put(chars[r], map.getOrDefault(chars[r], 0) + 1);
            // if (!isMapValid(k)) {
            //     map.put(chars[l], map.get(chars[l]) - 1);
            //     if (map.get(chars[l]) == 0)
            //         map.remove(chars[l]);
            //     l++;
            // }

            if (isMapValid(k)) {
                max = Math.max(max, r - l + 1);
            } else {
                if(pCount > 0) totalCount += pCount;
                // if(cCount > 0) totalCount += cCount;
                max = Math.max(max, totalCount);
            }
            r++;
            prev = curr;
        }

        return max;
    }

    private boolean isMapValid(int k) {
        for (var entry : map.values()) {
            if (entry < k)
                return false;
        }

        return true;
    }

    private boolean currCharLongEnough(char curr, int k) {
        int freq = map.getOrDefault(curr, 0);
        return freq >= k;
    }

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = 0;
        int max = 0;
        String ans = "";

        while(r < chars.length){
            if(!isPalindrome(l,r,chars)){
                l++;
            }

            if(isPalindrome(l,r,chars)){
                if(r - l + 1 > max){
                    max = r - l + 1;
                    ans = chars.toString().substring(l,r+1);
                }
                r++;
            }
        }

        return ans;
    }

    private boolean isPalindrome(int l, int r, char[] chars){
        String temp = Arrays.toString(chars).substring(l,r+1);
        return temp.contentEquals(new StringBuilder(temp).reverse());
    }

    Map<String, Boolean> map1 = new HashMap<>();
    private boolean helper(String s){
        for(int i = 0; i< s.length(); i++){
                var t = s.substring(0,i+1);
            System.out.println(t + "   " + s.substring(i+1));
                if(map1.getOrDefault(t, false) && helper(s.substring(i+1))) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println( new LeetCode().longestSubstring("bbaaacbd",3));
    }
}
