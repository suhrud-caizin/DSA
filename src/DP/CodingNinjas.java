package DP;

import java.util.*;

public class CodingNinjas {

    static long[] countDistinctWayToClimbStairMemo;
    private static long MODULO = 1000000007;
    public static long countDistinctWayToClimbStair(int nStairs) {
        // Write your code here.
        countDistinctWayToClimbStairMemo = new long[nStairs+1];
        Arrays.fill(mazeObstaclesMemo,-1);
        return helper(0, nStairs);
    }

    static long helper(int index,int nStairs){
        if(index == nStairs){
            return 1;
        }
        if(index > nStairs) return 0;
        if(countDistinctWayToClimbStairMemo[index] != -1) return countDistinctWayToClimbStairMemo[index];
        return countDistinctWayToClimbStairMemo[index] =  (helper(index+1,nStairs) + helper(index+2, nStairs))%MODULO;
    }



    //https://www.naukri.com/code360/problems/count-ways-to-reach-nth-stairs_798650?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
    public static long countDistinctWayToClimbStairIterative(int nStairs) {
        if (nStairs == 0) return 1; // Base case
        if (nStairs == 1) return 1; // Base case

        long prev2 = 1; // Ways to climb 0 stairs
        long prev1 = 1; // Ways to climb 1 stair

        for (int i = 2; i <= nStairs; i++) {
            long current = (prev1 + prev2) % MODULO;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    //https://www.naukri.com/code360/problems/frog-jump_3621012?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
    public static int frogJump(int n, int heights[]) {

        if(n == 1) return heights[0];
        if(n == 2) return Math.abs(heights[0]-heights[1]);

        int prev1 = Math.abs(heights[1]-heights[0]);
        int prev2 = 0;

        for (int i = 2; i < heights.length; i++) {
            int temp = prev1;
            prev1 = Math.min(prev1 + Math.abs(heights[i]-heights[i-1]),prev2+Math.abs(heights[i]-heights[i-2]));
            prev2 = temp;
        }

        return prev1;
    }


    //https://atcoder.jp/contests/dp/tasks/dp_b
    static int[] fjMemo;
    public static int frogJump(int n, int[] heights, int k){
        fjMemo = new int[n];
        Arrays.fill(fjMemo,-1);
        return fjHelper(heights,k,0);
    }

    public static Integer fjHelper(int[] heights, int k, int index){
        if(index >= heights.length) return null;
        if(fjMemo[index] != -1) return fjMemo[index];

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            var temp = fjHelper(heights,k,index+i);
            if(temp == null) continue;
            min = Math.min(Math.abs(heights[index+i]-heights[index])+temp,min);
        }
        return fjMemo[index]= min == Integer.MAX_VALUE ? 0 :min;
    }


    static int[] maxAdjacentMemo;
    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        // Write your code here.
        maxAdjacentMemo = new int[nums.size()];
         Arrays.fill(mazeObstaclesMemo, -1);
        return maximumNonAdjacentSumHelper(nums,0);
    }

    private static int maximumNonAdjacentSumHelper(ArrayList<Integer> nums, int index) {
        if(index >= nums.size()) return 0;
        if(maxAdjacentMemo[index] != -1) return maxAdjacentMemo[index];

        // include
        int included = nums.get(index) + maximumNonAdjacentSumHelper(nums,index+2);

        // exclude
        int excluded = maximumNonAdjacentSumHelper(nums,index+1);

        return maxAdjacentMemo[index] = Math.max(included,excluded);
    }



    //https://www.naukri.com/code360/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=SUBMISSION
    static int[][] ninjaTrainingMemo;
    public static int ninjaTraining(int n, int points[][]) {
        ninjaTrainingTabMemo = new int[n+1][points[0].length];
        return helper(points, 0, -1);
    }

    public static int helper(int points[][], int day, int prev){

        if(day == points.length) return 0;


        int max = 0;
        for (int i = 0; i < 3; i++) {
            if(i == prev) continue;


            if(ninjaTrainingTabMemo[day][i] == 0){
                ninjaTrainingTabMemo[day][i] = helper(points, day+1, i);
            }

            max = Math.max(points[day][i] + ninjaTrainingTabMemo[day][i],max);
        }

        return max;
    }


    static int[][] ninjaTrainingTabMemo;
    public static int ninjaTrainingTab(int n, int points[][]) {
        int[][] dp = new int[n][3]; // DP table to store max points for each day and task

        // Initialize the first day (base case)
        for (int i = 0; i < 3; i++) {
            dp[0][i] = points[0][i];
        }

        // Fill the DP table
        for (int day = 1; day < n; day++) {
            for (int task = 0; task < 3; task++) {
                dp[day][task] = points[day][task] + Math.max(
                        dp[day - 1][(task + 1) % 3],
                        dp[day - 1][(task + 2) % 3]
                );
            }
        }

        // Find the maximum value in the last day's tasks
        return Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
    }


//    https://www.naukri.com/code360/problems/total-unique-paths_1081470?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=SUBMISSION
    static int[][] uniquePathsMemo;
    public static int uniquePaths(int m, int n) {
        // Write your code here.
        uniquePathsMemo = new int[m][n];
        return helper(0,0,m,n);
    }

    public static int helper(int i, int j, int m, int n){
        if(uniquePathsMemo[i][j] != 0) return uniquePathsMemo[i][j];

        if(i == m-1) return 1;
        if(j == n-1) return 1;

        return uniquePathsMemo[i][j] = helper(i+1,j,m,n) + helper(i,j+1,m,n);
    }

    static int[][] mazeObstaclesMemo;
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>>mat) {
        // Write your code here.
        mazeObstaclesMemo = new int[n][m];
        for (var l : mazeObstaclesMemo){
            Arrays.fill(l,-1);
        }
        return helper(0,0,n,m,mat);
    }

    static int helper(int r, int c, int n, int m, ArrayList<ArrayList<Integer>> mat){
        if(mat.get(r).get(c) == -1) return 0;

        if(mazeObstaclesMemo[r][c] != -1) return mazeObstaclesMemo[r][c];

        if(r == n-1) return 1;
        if(c == m-1) return 1;

        return mazeObstaclesMemo[r][c] = helper(r+1,c,n,m,mat) + helper(r,c+1,n,m,mat);
    }


    //https://www.naukri.com/code360/problems/minimum-path-sum_985349?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=PROBLEM
    static int[][] minSumPathMemo;
    public static int minSumPath(int[][] grid) {
        // Write your code here.
        minSumPathMemo = new int[grid.length][grid[0].length];
        minSumPathMemo[grid.length-1][grid[0].length-1] = grid[grid.length-1][grid[0].length-1];
        return helper(0,0,grid);
    }

    public static int helper(int r, int c, int[][] grid){
        if(r == grid.length || c == grid[0].length) return Integer.MAX_VALUE;

        if(minSumPathMemo[r][c] != 0) return minSumPathMemo[r][c];
        // if(r+1 == grid.length && c+1 == grid[0].length) return memo[r][c];

        return minSumPathMemo[r][c]  = grid[r][c] + Math.min(helper(r+1,c,grid),helper(r,c+1,grid));

    }


    public static int minimumPathSum(int[][] triangle, int n) {
        // Write your code here.
        int[][] memo = new int[triangle.length][];

        for (int i = 0; i < triangle.length; i++) {
            memo[i] = new int[triangle[i].length];
            Arrays.fill(memo[i],Integer.MAX_VALUE-1);
        }

        memo[0][0] = triangle[0][0];


//        int small = Integer.MAX_VALUE;

        for (int r= 0; r+1 < triangle.length; r++) {
            for (int c =0; c < triangle[r].length; c++) {

                if(memo[r][c] + triangle[r+1][c] < memo[r+1][c]){
                    memo[r+1][c] = memo[r][c] + triangle[r+1][c];
                }

                if(memo[r][c] + triangle[r+1][c+1] < memo[r+1][c+1]){
                    memo[r+1][c+1] = memo[r][c] + triangle[r+1][c+1];
                }

//                if(r == triangle.length-2){
//                    int temp = Integer.min(memo[r+1][c],memo[r+1][c+1]);
//                    small = Integer.min(temp,small);
//                }

            }
        }
//        return small;

        int small = Integer.MAX_VALUE;
        for (int c = 0; c < memo[n - 1].length; c++) {
            small = Math.min(small, memo[n - 1][c]);
        }

        return small;
    }


    static int[][] getMaxPathSumMemo;
    public static int getMaxPathSum(int[][] matrix) {
        // Write your code here
        getMaxPathSumMemo = new int[matrix.length][matrix[0].length];

        for (int[] row : getMaxPathSumMemo) {
            Arrays.fill(row,Integer.MIN_VALUE);
        }
        // return helper(matrix,0,0);

        // if(row == 0){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            max = Integer.max(getMaxPathSumHelper(matrix,0,i),max);
            System.out.println(max);
        }

        return max;
        // }
    }

    public static int getMaxPathSumHelper(int[][] matrix, int row, int col){
        if(col < 0 || col > matrix[0].length-1) return Integer.MIN_VALUE;
        if(row == matrix.length) return 0;


        if(getMaxPathSumMemo[row][col] != Integer.MIN_VALUE) return getMaxPathSumMemo[row][col];

        int max = Integer.max(getMaxPathSumHelper(matrix,row+1,col),getMaxPathSumHelper(matrix,row+1,col-1));
        max = Integer.max(getMaxPathSumHelper(matrix,row+1,col+1),max);

        return getMaxPathSumMemo[row][col]=max+matrix[row][col];
    }




    //https://www.naukri.com/code360/problems/ninja-and-his-friends_3125885?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos

    static int[][][] chocolatesMemo;
    public static int maximumChocolates(int r, int c, int[][] grid) {
        chocolatesMemo = new int[r][c][c];
        for(var d1 : chocolatesMemo){
            for (var d2:d1){
                Arrays.fill(d2,-1);
            }
        }

        return maximumChocolatesHelper(0,0,c-1,grid);
    }

    public static int maximumChocolatesHelper(int r, int c1, int c2, int[][] grid){
        if(r == grid.length) return 0;
        if(c1 < 0 || c2<0 || c1 == grid[0].length || c2 == grid[0].length){
            return Integer.MIN_VALUE;
        }

        if(chocolatesMemo[r][c1][c2] != -1) return chocolatesMemo[r][c1][c2];

        int max = Integer.MIN_VALUE;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(c1 == c2){
                    max = Integer.max(grid[r][c1] + maximumChocolatesHelper(r+1, c1+i,c2+j, grid),max);
                }else{
                    max = Integer.max(grid[r][c1]+ grid[r][c2] + maximumChocolatesHelper(r+1, c1+i,c2+j, grid), max);
                }

            }
        }
        return chocolatesMemo[r][c1][c2] = max;
    }


    //https://www.naukri.com/code360/problems/subset-sum-equal-to-k_1550954?leftPanelTab=1%3Fsource%3Dyoutube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=SUBMISSION
    public static boolean subsetSumToK(int n, int k, int arr[]) {
        // Create a DP table
        boolean[][] dp = new boolean[n + 1][k + 1];

        // Base case: With sum = 0, we can always achieve it with an empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table row by row
        for (int i = 1; i <= n; i++) {
            for (int target = 1; target <= k; target++) {
                // Exclude the current element
                boolean notTake = dp[i - 1][target];

                // Include the current element if it does not exceed the target
                boolean take = false;
                if (arr[i - 1] <= target) {
                    take = dp[i - 1][target - arr[i - 1]];
                }

                dp[i][target] = take || notTake;
            }
        }

        // The answer is whether we can achieve the sum `k` using all `n` elements
        return dp[n][k];
    }

    //https://www.naukri.com/code360/problems/number-of-subsets_3952532?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=SUBMISSION
    static int modulo = 1000000007;
    public static int findWays(int[] num, int tar) {
        int n = num.length;
        int[] dp = new int[tar + 1];

        // Base case: A subset sum of 0 is always possible (empty subset).
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            // Traverse backwards to ensure we use each element only once.
            for (int j = tar; j >= num[i]; j--) {
                dp[j] = (dp[j] + dp[j - num[i]]) % modulo;
            }
        }
        return dp[tar];
    }

     int[] minimumElemMemo;
    public  int minimumElements(int num[], int x) {

        minimumElemMemo = new int[x+1];
//        return minimumElementsHelper(num,x);
        int val = minimumElementsHelper(num.length-1,x,num) ;
        return val > 10000 ? -1 : val;
    }

    public int minimumElementsHelper(int num[], int x){
        if (x == 0)
            return 0;
        if(x < 0) return -1;

        if (minimumElemMemo[x] != 0)
            return minimumElemMemo[x];

        int min = Integer.MAX_VALUE;
        for (int i : num) {
            int temp = minimumElementsHelper(minimumElemMemo, x - i);

            if (temp != -1)
                min = Integer.min(temp, min);

        }

        return minimumElemMemo[x] = min == Integer.MAX_VALUE ? -1 : min +1;
    }


    public int minimumElementsHelper(int index, int sum, int nums[]){

        if(index == 0){
            if(sum % nums[index] == 0) return sum / nums[index];
            else return 10001;
        }

        int take = Integer.MAX_VALUE;
        if(nums[index] <= sum ){
            take = 1 + minimumElementsHelper(index,sum-nums[index],nums);
        }

        int notTake = minimumElementsHelper(index - 1, sum, nums);

        return Math.min(take,notTake);
    }


//    https://www.naukri.com/code360/problems/unbounded-knapsack_1215029?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=PROBLEM
public int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
    // Write your code here.
    int[] dp = new int[w+1];
    dp[0] = 0;

    for (int i = 0; i < n; i++) {
        for (int j = weight[i]; j <= w; j++) {
            dp[j] = Math.max(dp[j], dp[j-weight[i]] + profit[i]);
        }
    }

    return dp[w];
}


//    https://www.naukri.com/code360/problems/ways-to-make-coin-change_630471?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=PROBLEM
    public long countWaysToMakeChange(int denominations[], int value){
        long[] dp = new long[value + 1];
        dp[0] = 1;

        for (int coin : denominations) {
            for (int i = coin; i <=  value; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }

        return dp[value];

    }



//    https://www.naukri.com/code360/problems/target-sum_4127362?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=PROBLEM
public int targetSum(int n, int target, int[] arr) {

    int sum = 0;
    for (int num : arr) {
        sum += num;
    }

    if(target > sum) return 0;
    if((sum - target) % 2 != 0) return 0;

    return targetSumHelper((sum - target)/2, arr);
}

    public int targetSumHelper(int target, int[] arr){
        int[] dp = new int[target +1];
        dp[0] = 1;

        for (int num : arr) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] + dp[i - num];
            }
        }

        return dp[target];
    }

    public int cutRod(int price[], int n) {
        // Write your code here.
        return cutRodHelper(0, price);
    }

    private int cutRodHelper(int index, int[] price){
        if(index == price.length) return 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; index + i < price.length; i++) {
            max = Integer.max(max, price[i] + cutRodHelper(index+i+1, price));
        }

        return max;
    }

    //https://www.naukri.com/code360/problems/rod-cutting-problem_800284?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
    public int cutRodTabulation(int price[], int n) {
        int[]dp = new int[n+1];

        for (int i = 1; i <= price.length; i++) {

            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                max = Math.max(dp[i-j] + price[j-1], max);
            }

            dp[i] = max;
        }

        return dp[n];
    }

    String[] dpCurr;
    String[] dpPrev;

    public String longestCommonSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        dpCurr = new String[m+1];
        dpPrev = new String[m+1];

        Arrays.fill(dpPrev,"");
        Arrays.fill(dpCurr,"");
        for(int i = 1; i <= n; i++ ){
            for(int j = 1; j <= m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dpCurr[j] = dpPrev[j-1] + s.charAt(i-1);
                }else{
                    dpCurr[j] = dpCurr[j-1].length() > dpPrev[j].length() ? dpCurr[j-1] : dpPrev[j];
                }
            }

            // Swap the references of dpPrev and dpCurr
            String[] temp = dpPrev;
            dpPrev = dpCurr;
            dpCurr = temp;
        }
        return dpPrev[m];
    }



    // https://www.naukri.com/code360/problems/longest-common-substring_1235207?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=SUBMISSION
     int max = Integer.MIN_VALUE;
     int[] dpCurrLcs;
     int[] dpPrevLcs;

    public int longestCommonSubstring(String s, String t) {
        int n = s.length();
        int m = t.length();
        dpCurrLcs = new int[m+1];
        dpPrevLcs = new int[m+1];
        for(int i = 1; i <= n; i++ ){
            for(int j = 1; j <= m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dpCurrLcs[j] = dpPrevLcs[j-1] + 1;
                }else{
                    dpCurrLcs[j] = 0;
                }

                max = Math.max(dpCurrLcs[j], max);
            }

            // Swap the references of dpPrev and dpCurr
            int[] temp = dpPrevLcs;
            dpPrevLcs = dpCurrLcs;
            dpCurrLcs = temp;
        }
        return max;
    }


//    public int deleteAndEarn(int[] nums) {
////        Map<Integer, Integer> map = new HashMap<>();
////
////        for(int num:nums){
////            map.put(num,map.getOrDefault(num,0) + 1);
////        }
//
//    }

    public static class Modifications{
        String word;
        int mods;

        public Modifications(String word, int mods) {
            this.word = word;
            this.mods = mods;
        }

        @Override
        public String toString() {
            return "Modifications{" +
                    "word='" + word + '\'' +
                    '}';
        }
    }
    public int minDistance(String word1, String word2) {

        List<Modifications> list = List.of(new Modifications(word2,0));

        for (int i = 0; i < word1.length(); i++) {
            List<Modifications> temp = new ArrayList<>();
            for (Modifications modifications : list) {
//                if(modifications.word.length()  <= i) continue;
                if (modifications.word.charAt(i) != word1.charAt(i)) {
                    if(modifications.word.length()  >= i){
                        String inserted = modifications.word.substring(0, i) + word1.charAt(i) + modifications.word.substring(i);
                        if (inserted.equals(word1))
                            return modifications.mods + 1;
                        temp.add(new Modifications(inserted,modifications.mods+1));
                    }

                    if(modifications.word.length()  > i){
                        String replaced = modifications.word.substring(0, i) + word1.charAt(i) + modifications.word.substring(i + 1);
                        if (replaced.equals(word1))
                            return modifications.mods + 1;
                        temp.add(new Modifications(replaced, modifications.mods+1));
                    }

                }else{
                    temp.add(modifications);
                }

            }
            System.out.println(temp);
            list = temp;
        }

        return -1;

    }


    public int numDistinct(String s, String t) {
        return helper(s,new StringBuilder(),t);
    }

    private int helper(String s, StringBuilder ss, String t){
        if(ss.toString().equals(t)) return 1;
        if(s.isEmpty()) return 0;
        // char c = ;

        return helper(s.substring(1), ss.append(s.charAt(0)), t) + helper(s.substring(1),ss,t);
    }

    public int coinChange(int[] coins, int sum){
        int [] dp = new int[sum+1];
        for (int i = 1; i <= dp.length; i++) {
            for (int coin : coins){
                if(i - coin >= 0){
                    dp[i] += dp[i-coin];
                }
            }
        }

        return dp[sum];
    }
    static record Pair(int tillVal, int diff){}

    public int longestArithSeqLength(int[] nums) {
        Map<Pair, Integer> map = new HashMap<>();

        int n = nums.length;
        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            int val = 1;
            for (int j = 0; j < i; j++) {
                val = map.getOrDefault(new Pair(nums[j],nums[i]-nums[j]),0)+1;
                map.put(new Pair(nums[i],nums[i]-nums[j]),val);
            }

            if(val > maxLen) maxLen = val;
        }
        return maxLen;
    }

    public static void main(String[] args) {
//        System.out.println(countDistinctWayToClimbStair(51));
//        var ans = ninjaTrainingTab(2,new int[][]{{10, 50, 1}, {5, 100, 11}});
//        System.out.println(ans);

//        System.out.println(getMaxPathSum(new int[][]{
//                {-9999, -9888, -9777, -9666, -9555},
//                {1, 10, 2, 4, 5}, {
//                -9999, -9888, -9777, -9666, -9555}, {
//                0, 0, 0, 0, 0}, {
//                -99, -98, -97, -96, -95}
//        }));

//        Integer.max;

//        System.out.println(
//        maximumChocolates(4,4,new int[][]{
//                {1, 5, 2, 2},
//                {2, 4, 5, 1},
//                {4, 4, 4, 5},
//                {2, 0, 4, 0}
//        }));
//        System.out.println(1);

//        System.out.println(
//        subsetSumToK(4,5,new int[]{4,3,2,1}));

//        System.out.println(
//        findWays(new int[]{5,2,6,4},));

//        System.out.println(
//        new CodingNinjas().minimumElements(new int[]{1,2,3},7));


//        System.out.println(
//                new CodingNinjas().cutRod(new int[]{2, 3, 5}, 3)
//        );
//
//        List.of(new int[]{1, 4});

//        var a = Arrays.asList(1,2,34);

//        int[] a = new int[]{1,3,4};
//
//        System.out.println(Arrays.toString(a));

        System.out.println(
        new CodingNinjas().longestCommonSubsequence("abc","abc")
        );



    }

}
