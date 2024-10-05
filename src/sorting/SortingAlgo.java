package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class SortingAlgo {

/*
* Best case time complexity O(n) i.e no. of Swaps
* Worst case complexity O(n^2) case of sorting descending sorted array ascending
* */
    public static void bubble(int[] arr){

        for (int i = 0; i < arr.length ; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length-i-1; j++) {

                if(arr[j] > arr[j+1]) {
                swap(arr, j , j+1);
                swapped = true;
                }

            }

            if(!swapped) break;
        }
    }


    /*
    * Best & Worst case time complexity O(n^2), we have to perform all the iterations to be able to declare array sorted
    * Algo is not stable, (stable : if sequence of integers of same value retained)
    * */
    public static void selection(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int last = arr.length-i-1;
            int max = getMaxIndex(arr, last);
            swap(arr,last, max);
        }
    }

    /*
    * Best case complexity O(n) no swaps
    * worst case complexity O(n^2) swaps
    * for i we are sorting array till position j i.e i+1
    * traverse j in reverse order till index 1
    * swap if arr[j] < arr[j-1]
    * as array is already sorted till i, if no swap is required it means stop the reverse traversal
    * */
    public static void insertion(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j > 0; j--) {
                if(arr[j] < arr[j-1]){
                    swap(arr,j,j-1);
                }else break;
            }
        }
    }

    /*
    * divide arrays till it becomes single element array
    * merge the two arrays using two pointers i & j => if left[i] is smaller, then add it to sorted array similar for right array
    * if anyone of i & j goes out of bound then break loop and add the remaining elements from leftover array as it is to result array
    * as both the left & right arrays are already sorted
    * time complexity = O(n * logn) for all the cases
    * space complexity = O(n)
    *
    * */
    public static int[] mergeSort(int[] arr){

        if(arr.length == 1) return arr;
        int mid = arr.length / 2;

        //divide
        var left = mergeSort(Arrays.copyOfRange(arr,0,mid));
        var right = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));

        //merge
        return merge(left,right);
    }


    /*
    * two pointers i & j running in opposite direction
    * select a pivot element (any)
    * all the elements smaller than pivot keep to left
    * all elements larger than pivot keep to right
    * pivot is at correct position
    * repeat this for all the elements
    *
    * best case complexity = O(n*long(n))
    * worst case complexity = O(n^2)
    *
    * not STABLE
    *
    * */
    public static void quickSort(int[] arr,int low, int high){

        if(low > high) return;

        int s = low;
        int e = high;

        int m = (s + e)/2;
        int pivot = arr[m];

        while (s <= e){
            while (arr[s] < pivot) s++;
            while (arr[e] > pivot) e--;

            if(s <= e){
                swap(arr,s,e);
                s++;
                e--;
            }
        }

        quickSort(arr,low,e);
        quickSort(arr,s,high);
    }


    /*
    public static void mergeSortInplace(int[] arr, int start, int end){
        System.out.println(start + " " + end);
        if(end - start == 1) return;
        int mid = ((start + end) / 2);

        //divide
         mergeSortInplace(arr,start, mid);
         mergeSortInplace(arr,mid, end);

        //merge
        mergeInplace(arr, start, mid, end);
//        return arr;
    }


    private static void mergeInplace(int[] arr, int start, int mid, int end) {
        int[] sorted = new int[end - start];
        int i = start, j = mid, k = 0;

        while (i < mid && j < end) {
            if (arr[i] < arr[j]) {
                sorted[k] = arr[i];
                i++;
            } else {
                sorted[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i < mid) {
            sorted[k++] = arr[i++];
        }

        while (j < end) {
            sorted[k++] = arr[j++];
        }

        for (int l = 0; l < sorted.length; l++) {
            arr[l + start] = sorted[l];
        }
    }*/






    public static void mergeSortInPlace(int[] arr, int s, int e) {
        System.out.println(s + " " + e);
        if (e - s == 1) {
            return;
        }

        int mid = (s + e) / 2;

        mergeSortInPlace(arr, s, mid);
        mergeSortInPlace(arr, mid, e);

        mergeInPlace(arr, s, mid, e);
    }

    private static void mergeInPlace(int[] arr, int s, int m, int e) {
        int[] mix = new int[e - s];

        int i = s;
        int j = m;
        int k = 0;

        while (i < m && j < e) {
            if (arr[i] < arr[j]) {
                mix[k] = arr[i];
                i++;
            } else {
                mix[k] = arr[j];
                j++;
            }
            k++;
        }

        // it may be possible that one of the arrays is not complete
        // copy the remaining elements
        while (i < m) {
            mix[k] = arr[i];
            i++;
            k++;
        }

        while (j < e) {
            mix[k] = arr[j];
            j++;
            k++;
        }

        for (int l = 0; l < mix.length; l++) {
            arr[s+l] = mix[l];
        }
    }



    private static int[] merge(int[] left, int[] right){
        int[] sorted = new int[left.length+ right.length];
        int i=0,j=0,k=0;

        while(i < left.length && j < right.length){
            if(left[i] > right[j]){
                sorted[k] = right[j];
                j++;
            }else{
                sorted[k] = left[i];
                i++;
            }
            k++;
        }

        while(i< left.length){
            sorted[k++] = left[i++];
        }

        while(j< right.length){
            sorted[k++] = right[j++];
        }
        return sorted;
    }


    /*
    * can be used for lists where data is in a range
    * time complexity is O(n) for all the cases
    *
    * */
    public static void cyclic(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
                while(!isCorrectPosition(arr,i)){
                    swap(arr,i,arr[i]-1);
                }
        }
    }

    public int missingNumber(int[] arr) {
        int i = 0;
        while( i < arr.length){
            if(arr[i] < arr.length && arr[i]!= i) {
                swap(arr,i,arr[i]);
            }
            else i++;
        }

        for (int j = 0; j < arr.length; j++) {
            if(arr[j]!= j) return j;
        }

        return arr.length;

    }

    public int firstMissingPositive(int[] arr) {
        int i = 0;
        while( i < arr.length){
            if((arr[i] > 0) && (arr[i] <= arr.length) && arr[i]!= i+1) {
                int tmp = arr[i];
                if(arr[tmp-1] != tmp){
                    swap(arr,i,arr[i]-1);
                }else i++;
            }
            else i++;
        }

        for (int j = 0; j < arr.length; j++) {
            if(arr[j]!= j+1) return j+1;
        }

        return arr.length+1;

    }

    public List<Integer> findDisappearedNumbers(int[] arr){
        int i = 0;
        while( i < arr.length){
            int tmp = arr[i];
            if(arr[tmp-1] != tmp) {
                swap(arr,i,tmp-1);
            }
            else i++;
        }

        List<Integer> l = new ArrayList<>();

        for (int j = 0; j < arr.length; j++) {
            if(arr[j]!= j+1) l.add(j+1);
        }

        return l;
    }

    public int[] findErrorNums(int[] arr){
        int i = 0;
        while( i < arr.length){
            int tmp = arr[i];
            if(arr[tmp-1] != tmp) {
                swap(arr,i,tmp-1);
            }
            else i++;
        }


        int[] err = new int[2];
        for (int j = 0; j < arr.length; j++) {
            if(arr[j]!= j+1) {
            err[0] = arr[j];
            err[1] = j + 1;
            }
        }

        return err;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> l= new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;

            int start = i+1;
            int end = nums.length-1;
            while(start<end){
                int sum = nums[start]+nums[end]+nums[i];
                if(sum == 0) {
                    l.add(Arrays.asList(nums[i],nums[start],nums[end]));

                    while(start < end && nums[start] == nums[start+1]){
                            start++;
                    }

                    while(start < end && nums[end] == nums[end-1]){
                        end--;
                    }

                    start++;
                    end--;
                } else if (sum < 0) {
                    start++;
                }else{
                    end--;
                }
            }
        }
            return l;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> l= new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-3; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < nums.length-2; j++) {
                if(j> i+1 && nums[j] == nums[j-1]) continue;

                int start = j+1;
                int end = nums.length-1;
                while(start<end){
                    int sum = nums[start]+nums[end]+nums[i]+nums[j];
                    if(sum == target) {
                        l.add(Arrays.asList(nums[i],nums[j],nums[start],nums[end]));

                        while(start < end && nums[start] == nums[start+1]){
                            start++;
                        }

                        while(start < end && nums[end] == nums[end-1]){
                            end--;
                        }

                        start++;
                        end--;
                    } else if (sum < target) {
                        start++;
                    }else{
                        end--;
                    }
                }
            }
        }
        return l;
    }

//    public static List<List<Integer>> threeSum2(int[] nums) {
//        List<List<Integer>> l= new ArrayList<>();
//        Arrays.sort(nums);
//
//        for (int i = 0; i < nums.length-2; i++) {
//            if(i>0 && nums[i] == nums[i-1]) continue;
//            for (int j = i+1; j < nums.length-1 ; j++) {
//                if(j> i+1 && nums[j] == nums[j-1]) continue;
//                for (int k = j+1; k < nums.length; k++) {
//                    if(k>j+1 && nums[k] == nums[k-1]) continue;
//
//                    if(nums[i] + nums[j] + nums[k] == 0 ) l.add(Arrays.asList(nums[i],nums[j],nums[k]));
//                }
//            }
//        }
//
//        return l;
//    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> groupedAnagrams = new HashMap<>();

        for (String str : strs) {
            char[] tmp = str.toCharArray();
            Arrays.sort(tmp);
            String key = new String(tmp);  // Use sorted string as the key

            // If the key does not exist, create a new ArrayList and add the string
            groupedAnagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(groupedAnagrams.values());
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][0];

        // Sort the intervals by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        ArrayList<int[]> result = new ArrayList<>();

        // Add the first interval to the result list
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // Get the last interval added to the result list
            int[] lastAddedInterval = result.get(result.size()-1);

            // If the current interval overlaps with the last added interval, merge them
            if (lastAddedInterval[1] >= intervals[i][0]) {
                lastAddedInterval[1] = Math.max(lastAddedInterval[1], intervals[i][1]);
            } else {
                // Otherwise, add the current interval to the result list
                result.add(intervals[i]);
            }
        }

        // Convert the LinkedList to a 2D array and return it
        return result.toArray(new int[result.size()][]);
    }

    public static int[][] convertListToArray(List<Integer[]> list) {
        // Create the int[][] with the same size as the List
        int[][] array = new int[list.size()][];

        // Iterate over the list and convert each Integer[] to int[]
        for (int i = 0; i < list.size(); i++) {
            Integer[] temp = list.get(i);
            array[i] = Arrays.stream(temp).mapToInt(Integer::intValue).toArray();  // Convert Integer[] to int[]

        }
        System.out.println(Arrays.deepToString(array));
        return array;
    }
    public int threeSumClosest(int[] nums, int target) {
//        int delta = Integer.MAX_VALUE;
        int currentSum = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            boolean match = false;
            if(i>0 && nums[i] == nums[i-1]) continue;

            int start = i+1;
            int end = nums.length-1;
            while(start<end){
                int sum = nums[start]+nums[end]+nums[i];
                if(sum == target) {
                    match =true;
                    currentSum = sum;
                    break;
                } else if (sum < 0) {
                    if(Math.abs(target-sum) < Math.abs(target - currentSum)){
                        currentSum = sum;
                    }
                    start++;
                }else{
                    if(Math.abs(target-sum) < Math.abs(target - currentSum)){
                        currentSum = sum;
                    }
                    end--;
                }
            }
            if(match) break;
        }
        return currentSum;
    }

    public int findDuplicate(int[] arr){
        int i = 0;
        while( i < arr.length){

            if(arr[i]!= i+1){

                int tmp = arr[i];
                if(arr[tmp-1] != tmp) {
                    swap(arr,i,tmp-1);
                } else return tmp;
            }
            else i++;
        }

        return -1;
    }


    //IMP concept
    public static ListNode insertionSortList(ListNode head) {
        ListNode helper = new ListNode(0);
        ListNode prev = helper;
        ListNode curr = head;
        ListNode next = null;

        while(curr != null){
            next = curr.next;

            while(prev.next != null && prev.next.val < curr.val){
                prev = prev.next;
            }

            curr.next = prev.next;
            prev.next = curr;
            curr = next;
            prev = helper;
        }

        return helper.next;
    }

    public static void printList(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static String largestNumber(int[] arr) {
        String[] strNumbers = Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);


        Arrays.sort(strNumbers, (a, b) -> {
            if (a.length() != b.length()) {
                // Compare based on length first (shorter strings come first)
                return Integer.compare(a.length(), b.length());
            } else {
                // If lengths are the same, compare lexicographically in reverse order
                return b.compareTo(a);
//                b.co

            }
        });

       StringBuilder res = new StringBuilder();

       for(String s : strNumbers){
           res.append(s);
       }

        return res.toString();
    }


    public static void mergeLC(int[] nums1, int m, int[] nums2, int n) {
                                                                                        // 1 2 3 0 0 0   2 5 6
            for (int i = 0; i < nums2.length; i++) {
                int j;
                for (j = 0; j < m+i; j++) {
                    if(nums1[j] >= nums2[i]) {

                        int itr = m+i;
                        while(itr > j){
                            nums1[itr] = nums1[itr - 1];
                            itr--;
                        }
                        break;
                    }
                }
                nums1[j] = nums2[i];
            }
    }

    public static void mergeLCMS(int[] nums1, int m, int[] nums2, int n) {
        // 1 2 3 0 0 0   2 5 6
        int i= 0,j = 0,k=0;
        int[] nums1cp = Arrays.copyOf(nums1,nums1.length);
        while(i < m && j<n){
                if(nums1cp[i] < nums2[j]){
                            nums1[k++] = nums1cp[i++];
                }else{
                    nums1[k++] = nums2[j++];
                }
        }

        while(i<m){
            nums1[k++] = nums1cp[i++];
        }


        while(j<n){
            nums1[k++] = nums2[j++];
        }

    }

    public static void mergeLCT(int[] nums1, int m, int[] nums2, int n) {

        int i = m-1;
        int j = n-1;
        int k = m + n - 1;

        while(j>=0){
            if(nums2[j] > nums1[i]){
                nums1[k--] = nums2[j--];
            }else{
                nums1[k--] = nums1[i--];
            }
        }

    }

//    public static int majorityElement3(int[] nums) {
//
//        int cnt = 0;
//        int candidate = 0;
//
//        for (int num : nums){
//
//            if(cnt == 0) candidate = num;
//
//            if(candidate == num) cnt++;
//            else cnt--;
//        }
//    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }


    public static int majorityElement(int[] nums) {
        Map<Integer,Integer> ft = new HashMap<>();
        
        for (int num : nums){
            int val = 0;
            if(!ft.containsKey(num)) val = 1;
            else val = ft.get(num) + 1;

            if(val > nums.length / 2) return num;

            ft.put(num,val);
        }
        return -1;

    }

    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i< nums.length-1;i++){
            if(nums[i]== nums[i+1]) return true;
        }
        return false;
    }


    public static boolean containsDuplicate2(int[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length-i-1; j++) {

                if(arr[j] == arr[j+1]) return true;
                else if(arr[j] > arr[j+1]) {
                    swap(arr, j , j+1);
                    swapped = true;
                }

            }

            if(!swapped) break;
        }
        return false;
    }


    public static int[] intersection(int[] arr1, int[] arr2) {
//        int n1 = arr1.length;
//        int n2 = arr2.length;

        HashMap<Integer,Boolean> hp1 = new HashMap();

        for(int i=0; i< arr1.length; i++){
            hp1.put(arr1[i],true);
        }

        HashSet<Integer> hp2 = new HashSet<>();

        for(int i=0; i< arr2.length; i++){
            if(hp1.containsKey(arr2[i])) hp2.add(arr2[i]);
        }

//            hp2.to
        int ans[] = new int[hp2.size()];
        int idx = 0;

        for(var a : hp2){
            ans[idx++] = a;
        }
        return ans;
    }


    public static int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hm= new HashMap<>();
        List<Integer> l = new ArrayList<>();
        for (int num:nums1){
            if(!hm.containsKey(num)){
                hm.put(num,1);
            }else{
                hm.computeIfPresent(num,(k,v)->v+1);
            }
        }

        for(int num : nums2){
            int freq = hm.getOrDefault(num,0);
            if( freq > 0){
                l.add(num);
                hm.put(num, freq-1);
            }
        }
        return l.stream().mapToInt(Integer::intValue).toArray();

    }



    public static int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        List<Integer> intersection = new ArrayList<>();
        while(i<nums1.length && j<nums2.length){
            while(i< nums1.length && nums1[i]<nums2[j]){
                i++;
            }

            while(j < nums2.length && nums1[i]>nums2[j]){
                j++;
            }

            if(nums1[i] == nums2[j]) {
            intersection.add(nums1[i]);
                i++;
                j++;
            }

        }
        int[] result = new int[intersection.size()];
        for (int k = 0; k < intersection.size(); k++) {
            result[k] = intersection.get(k);
        }

        return result;
    }


    public static int largestPerimeter(int[] nums) {
        int maxPerimeter = 0;
        for (int i = 0; i < nums.length -2; i++) {
            if(nums[i]+nums[i+1] > nums[i+2]){
                int p = nums[i]+nums[i+1] + nums[i+2];
                if(maxPerimeter < p) maxPerimeter = p;
            }
        }
        return maxPerimeter;
    }

    public static int thirdMax2(int[] arr) {
        HashSet<Integer> hs = new HashSet<>();
        List<Integer> al =new ArrayList<>();
        for (int num : arr){
            if(!hs.contains(num)) al.add(num);hs.add(num);
        }

        arr= al.stream().mapToInt(Integer::intValue).toArray();

        int n = arr.length-1;
        int l = 1;
        if(arr.length >= 3){
            n= arr.length-3;
            l = 3;
        }
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) swap(arr,j,j+1);
            }
        }

        return arr[n];
    }

    public static int thirdMax(int[] arr) {
        int s = 0;
        int e = arr.length-1;
        int m = e;

        HashSet<Integer> hs = Arrays.stream(arr)
                .boxed()   // Convert int[] to Stream<Integer>
                .collect(Collectors.toCollection(HashSet::new));

        arr = hs.stream().mapToInt(Integer::intValue).toArray();


        if(arr.length >= 3)  m = arr.length-3;

        int pivot = arr[m];

        while (s <= e){
            while (arr[s] < pivot) s++;
            while (arr[e] > pivot) e--;

            if(s <= e){
                swap(arr,s,e);
                s++;
                e--;
            }
        }

        return arr[m];
    }


    public static int findContentChildren(int[] g, int[] s) {
        int cnt = 0;
        // int n = Math.min(s.length, g.length);
        Arrays.sort(g);
        Arrays.sort(s);
        int i =0;
        int slen = s.length;
        int glen = g.length;
        for (int j = 0; j < slen && i<glen;) {
            if(s[j] >= g[i]) {
                cnt++;
                i++;
                j++;
                continue;
            }

            while(j<glen && s[j] < g[i]){
                j++;
            }
        }

        return cnt;
    }

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int s = nums[0]*nums[1]*nums[len-1];
        int e = nums[len-1]*nums[len-2]*nums[len-3];
        return Math.max(s,e);
    }

    public int[] sortArrayByParity(int[] arr) {
        int s = 0;
        int e = arr.length-1;

        while(s<e){
            while(arr[s]%2 == 0){
                s++;
            }

            while(arr[e]%2!= 0){
                e--;
            }
            if(s> arr.length-1 || e<0) break;
            swap(arr,s,e);
            s++;
            e--;
        }

        return arr;
    }


    public int[] sortArrayByParityII(int[] arr) {
        int i = 0;
        int j = 1;

        while(i< arr.length && j < arr.length){
            if (arr[i] % 2 == 0) {  // If nums[i] is even, increment i.
                i+=2;
            } else if (arr[j] % 2 != 0) {  // If nums[j] is odd, decrement j.
                j+=2;
            } else {  // If nums[i] is odd and nums[j] is even, swap them and update pointers.
                swap(arr, i, j);
                i+=2;
                j+=2;
            }
        }

        return arr;
    }

    private static int[] mergep(int[] left, int[] right){
        int[] sorted = new int[left.length+ right.length];
        int i=0,j=0,k=0;

        while(i < left.length && j < right.length){
            if(left[i]%2 != right[j]%2){
                sorted[k] = right[j];
                j++;
            }else{
                sorted[k] = left[i];
                i++;
            }
            k++;
        }

        while(i< left.length){
            sorted[k++] = left[i++];
        }

        while(j< right.length){
            sorted[k++] = right[j++];
        }
        return sorted;
    }




    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> l = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length-1; i++) {
                int absDiff =  Math.abs(arr[i] - arr[i+1]);
                if(absDiff < minDiff) l.clear();
                l.add(new ArrayList<>(Arrays.asList(arr[i],arr[i+1])));
        }

        return l;

    }


    public static int[] arrayRankTransform(int[] arr) {
            HashMap<Integer,Integer> hm = new HashMap<>();
            var arrcp = Arrays.copyOf(arr,arr.length);
//        System.out.println(Arrays.toString(arrcp));
            Arrays.sort(arr);
            int rank = 0;

        for (int i = 0; i < arr.length; i++) {
            if(i > 0 ? (arr[i] != arr[i-1]) : i == 0){
                    hm.put(arr[i],++rank);
            }
        }

        for (int i = 0; i < arrcp.length; i++) {
                arrcp[i] = hm.get(arrcp[i]);
        }

        return arrcp;
    }


//    public int[] sortByBits(int[] arr) {
//
//    }

    static HashMap<Integer,Integer> meme = new HashMap<>();

    public static int getNumberOfOnes(int input){
        if(meme.containsKey(input)) return meme.get(input);

        int cnt = 0;
        while(input > 0){
            if(input%2==1) cnt++;
            input/=2;
        }
        meme.put(input,cnt);


        return cnt;
    }

    public int[] frequencySort(int[] nums) {
        record T(int num, int freq) {}
        int[] ans = new int[nums.length];
        int ansIndex = 0;
        Map<Integer, Integer> count = new HashMap<>();
        Queue<T> heap = new PriorityQueue<>((a, b) //
                -> a.freq == b.freq ? Integer.compare(b.num, a.num)
                : Integer.compare(a.freq, b.freq));

        for (final int num : nums)
            count.merge(num, 1, Integer::sum);

        for (Map.Entry<Integer, Integer> entry : count.entrySet())
            heap.offer(new T(entry.getKey(), entry.getValue()));

        while (!heap.isEmpty()) {
            final int num = heap.peek().num;
            final int freq = heap.poll().freq;
            for (int i = 0; i < freq; ++i)
                ans[ansIndex++] = num;
        }

        return ans;
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        var cpArr = Arrays.copyOf(nums,nums.length);
        Arrays.sort(nums);
        HashMap<Integer,Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length ; i++) {
            if(i>0 && nums[i] != nums[i-1]){
                hm.put(nums[i], i);
            }else if(i == 0){
                hm.put(nums[i], i);
            }
        }

        System.out.println(hm);

        for (int i = 0; i < cpArr.length; i++) {
            cpArr[i] = hm.get(cpArr[i]);
        }

        return cpArr;
    }


    public int maxProduct(int[] nums) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > largest){
                largest = nums[i];
            }else if(nums[i] > secondLargest){
                secondLargest = nums[i];
            }
        }

        return (secondLargest-1) * (largest-1);
    }

//    public static double average(int[] salary) {
//        int min = Integer.MAX_VALUE;
//        int max = Integer.MIN_VALUE;
//        double sum = 0;
//
//        for (int i = 0; i < salary.length; i++) {
//                if(salary[i] > max){
//                    max = salary[i];
//                }else if(salary[i] < min){
//                    min = salary[i];
//                }
//                    sum+=salary[i];
//        }
//        System.out.println("max-"+max+" min-"+min+" sum-"+sum);
//
//        var num = sum - max - min;
//        var deno =  salary.length-2;
//
//        var ans1 = num/deno;
//        var ans2 = num%deno;
//        return ans;
//    }

    public int specialArray(int[] nums) {
        Arrays.sort(nums);

        int start = 1;
        int end = nums.length;

        for (int i = start; i <= end; i++) {
            int j = 0;
            while(i < nums[j]) j++;

            if(i>0){
                if(nums.length -1 -i == i && nums[i]!= nums[i-1]) return i;
            }else if(i==0){
                if(nums.length -1 -i == i) return i;
            }
        }
        return -1;
    }


    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        // do count sort for every digit place
        for(int exp = 1; max/exp > 0; exp *= 10) {
            countSort2(arr, exp);
        }
    }

    private static void countSort2(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for(int i=0; i<n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        System.out.println("\nCount array for " + exp + " : " + Arrays.toString(count));

        for(int i=1; i<10; i++) {
            count[i] = count[i] + count[i-1];
        }

        System.out.println("Updated count array " + Arrays.toString(count));

        for(int i=n-1; i>=0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.out.println("Output array " + Arrays.toString(output));

        System.arraycopy(output, 0, arr, 0, n);

    }


    private static boolean isCorrectPosition(int[]arr,int pos){
        return pos+1 == arr[pos];
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int getMaxIndex(int[] arr, int end) {
        int maxIndex= 0;
        for (int i = 1; i <= end ; i++) {
            if(arr[i] > arr[maxIndex]) maxIndex = i;
        }

        return maxIndex;
    }


}

