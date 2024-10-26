package Recursion.Questions;

import sorting.ListNode;

import java.util.*;
import java.util.stream.Collectors;

import static Recursion.backtracking.Backtracking.isSafe;

public class LeetCode {
    static List<List<Integer>> ans = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumHelper(0,candidates,target,new ArrayList<>());
        return ans;
    }

    static void combinationSumHelper(int i,int[] candidates,int target, ArrayList<Integer> carry){
        if(i == candidates.length) return;
        if(target < 0 ) return;

        if(target == 0){
            ans.add(carry);
            return;
        }


        var temp = new ArrayList<>(carry);
        carry.add(candidates[i]);

        combinationSumHelper(i,candidates,target - candidates[i],carry);
        combinationSumHelper(i+1,candidates,target ,temp);
    }

    public static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        return existHelper(0,0, board,word,visited);
    }

    private static boolean existHelper(int i, int j, char[][] board, String word,boolean[][] visited) {
        if(word.isEmpty()) return true;
        if(i == board.length) return false;
        if(j ==  board[i].length){
            return existHelper(i+1,0,board,word,visited);
        }

        if(board[i][j] == word.charAt(0)){
            visited[i][j] = true;
            var p = word.substring(1);
            var ans = false;
            
            if(isValid(i+1,j,board) && !visited[i+1][j]){
                ans = ans || existHelper(i+1,j,board,p,visited);
            }

            if (isValid(i-1,j,board) && !visited[i-1][j]) {
                ans = ans || existHelper(i-1,j,board,p,visited);
            }

            if (isValid(i,j+1,board) && !visited[i][j+1]) {
                ans = ans || existHelper(i,j+1,board,p,visited);
            }

            if(isValid(i,j-1,board) && !visited[i][j-1]) {
                ans = ans || existHelper(i,j-1,board,p,visited);
            }


            visited[i][j] = false;
            return ans;
        }else{
            return existHelper(i,j+1,board,word,visited);
        }
    }

    static boolean isValid(int r,int c,char[][]board){
        return r >=0 && r< board.length && c>=0 && c< board[0].length;
    }

    public static int findTargetSumWays(int[] nums, int target) {
            return targetSumWaysHelper(nums,target,0,0);
    }

    private static int targetSumWaysHelper(int[] nums, int target, int actual, int i){
        if(actual == target && i == nums.length -1) return 1;
        if(i == nums.length) return 0;
        return targetSumWaysHelper(nums,target,actual+(-1*nums[i]),i+1) + targetSumWaysHelper(nums,target,actual+nums[i],i+1);
    }


    public static char findKthBit(int n, int k) {
        if(n==1) return '0';
        int length = (int) (Math.pow(2,n)-1);
        int m = length/2;
        if(k == m) return '1';
        else if (k < m) {
            return findKthBit(n-1,k);
        }else{
            char tmp = findKthBit(n-1,k-1-m);
            return tmp == '0' ? '1' : '0';
        }
    }

    private static String nthStringHelper(int n){
        if(n == 1) return "0";
        String prev = nthStringHelper(n-1);
        return prev+"1"+invert(prev);
    }

    private static String invert(String input){
        input = new StringBuffer(input).reverse().toString();
        char[] arr = input.toCharArray();

        for (int i = 0;i<arr.length; i++) {
            if(arr[i] == '0'){
                arr[i] = '1';
            }else{
                arr[i] = '0';
            }
        }

        return new String(arr);
    }

    public int kthGrammar(int n, int k) {
        if(n == 2 && k == 2) return 1;
        if(n==2 && k == 1) return 0;

        int length = (int) Math.pow(2,n-1);
        int mid = length/2;

        if(k<mid){
            return kthGrammar(n-1,k);
        }else{
            return invertEven(n,kthGrammar(n-1,mid-(k-mid)));
        }
    }

    private int invertEven(int n,int num) {
        if(n%2==0){
            if(num == 0) return 1;
            return 0;
        }else{
            return num;
        }
    }


    public static long modulo = 1000000007;
    public int countGoodNumbers(long n) {
        /*
        formula
        number of ways we can arrange p numbers in n places where p can be smaller than n = p^n (allowing repetations)
         */

        if( n%2 == 0){
            return (int) (power(5,n/2) * power(4,n/2) * modulo);
        }else{
            return (int) (power(4,n/2) * power(5,n/2+1) * modulo);
        }
    }

    private long power(int base, long pow) {
                if(pow == 0) return 1;

               long temp = power(base,pow/2);
               if(pow%2 == 0){
                   return temp * temp % modulo;
               }else{
                   return temp * temp * base % modulo;
               }

    }

    private static long power(long base, long pow) {
        if(pow == 0) return 1;

        long tmp = ((base%modulo)*(base%modulo))%modulo;

    if(pow%2 == 0){
        return power(tmp,pow/2);
    }else{
        return ((power(tmp,pow/2) * (base%modulo)) % modulo);
    }
    }

    public double myPow(double x, int n) {
        long pow = Math.abs((long) n);
        double res =  Math.pow(x, pow);
        return (n < 0) ? (1/res) : res;
    }



    public static int minNonZeroProduct(int p) {
        long length = (long)(Math.pow(2,p))-1;
        return (int)((power(length - 1,(length-1)/2)) * (length % modulo ) % modulo);
    }


    static Map<Integer,Integer> m;
    public static int numSquares(int n) {
        List<Integer> l = new ArrayList<>();
        m = new HashMap<>();
        int i = 1;
        int ctr = 1;
        while(i <= n){
            l.add(i);
            ctr++;
            i = (int) Math.pow((ctr),2);
        }

//        System.out.println(l);

        int ans = numSquareHelper(l,0,n,0);
//        System.out.println(m);
        return ans;
    }

    static int numSquareHelper(List<Integer> l,int itr, int num, int cnt){
        if(m.containsKey(num)) return m.get(num);

        if(num == 0){
            return cnt;
        }

        if(num < 0){
            return Integer.MAX_VALUE;
        }

        if(itr == l.size()) return Integer.MAX_VALUE;


        int minCnt = Math.min(numSquareHelper(l,itr,num-l.get(l.size()-1-itr),cnt+1),
                numSquareHelper(l,itr+1,num,cnt));

        m.put(num,minCnt);

        return minCnt;
    }


    public int numSquares2(int n) {
        int[] memo = new int[n+1];
        return solve(memo,n);
    }

    private int solve(int[] memo, int n){
        if(n <= 0) return 0;
        if(memo[n]!=0) return memo[n];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i*i <= n ; i++) {
            min = Integer.min(1+solve(memo,(n-i*i)),min);
        }
        memo[n] = min;
        return min;
    }


    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        String sps = "";
        for (char c : s.toCharArray()){
            if(c!=']'){
                stack.push(String.valueOf(c));
            }else{
                StringBuilder p = new StringBuilder();
                while(true){
                    var elem = stack.pop();
                    if(elem == "["){
                        sps = reverseAndRepeate(p,stack.pop());
                        stack.push(sps);
                        break;
                    }
                    p.append(elem);
                }
            }
        }

        return sps;
    }

    private String reverseAndRepeate(StringBuilder p, String pop) {
        return "";
    }

    Map<String,List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        if(map.containsKey(expression)) return map.get(expression);

        var ans = new ArrayList<Integer>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if(c == '+' || c == '-' || c == '*'){
                    var left = expression.substring(0,i);
                    var right = expression.substring(i+1);

                    var l1 = diffWaysToCompute(left);
                    var l2 = diffWaysToCompute(right);
                    for (Integer num1 : l1){
                        for (Integer num2 : l2){
                            if(c == '+'){
                                ans.add(num1+num2);
                            }else if(c == '-'){
                                            ans.add(num1-num2);
                            }else{
                                ans.add(num1*num2);
                            }
                        }
                    }


            }

        }

        if(ans.isEmpty()){
          ans.add(Integer.parseInt(expression));
        }
        map.put(expression,ans);
        return ans;
    }

    public boolean predictTheWinner(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
        }

        int turn = 1;
        int firstScore = 0;
        int lastScore = 0;

        while(!list.isEmpty()){
            Integer first = list.get(0);
            Integer last = list.get(list.size()-1);
            int max;

            if(first > last){
                max = first;
                list.remove(0);
            }else{
                max = last;
                list.remove(list.get(list.size()-1));
            }


                if(turn % 2 == 0){
                    lastScore += max;
                }else{
                    firstScore += max;
                }

                turn++;
        }

        return firstScore >= lastScore;
    }


    public boolean predictTheWinner2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return winnerHelper(list,0,0,0,true) || winnerHelper(list, list.size()-1,0,0,true);
    }

    private boolean winnerHelper(List<Integer> list, int i, int firstScore, int secScore, boolean firstsTurn){
        if(list.isEmpty()){
            return firstScore >= secScore;
        }
        var node = list.remove(i);

        if(firstsTurn){
            firstScore += node;
        }else{
            secScore += node;
        }
        return winnerHelper(list,0,firstScore,secScore,!firstsTurn) || winnerHelper(list, list.size()-1, firstScore,secScore,!firstsTurn);
    }

    public boolean predictTheWinner3(int[] nums) {
        int totalScore = 0;
        int[][] memo = new int[nums.length][nums.length];
        for (int[] row:memo){
            Arrays.fill(row,-1);
        }
        for (int num:nums){
            totalScore += num;
        }

        int score = getMaxScorePossible(nums,0,nums.length-1, memo);
        int opponentScore = totalScore - score;

        return score >= opponentScore;

    }

    private int getMaxScorePossible(int[] nums, int i, int j, int[][] memo) {
        if(i > j){
            return 0;
        }
        if(memo[i][j] != -1){
            return memo[i][j];
        }

        var left = nums[i] +   Math.min(getMaxScorePossible(nums,i+2,j, memo),getMaxScorePossible(nums,i+1,j-1, memo));
        var right = nums[j] + Math.min(getMaxScorePossible(nums,i,j-2, memo), getMaxScorePossible(nums,i+1,j-1, memo));

        int maxScore = Math.max(left,right);
        return memo[i][j] = maxScore;
    }


    static List<List<Integer>> data;
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        data= new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(new ArrayList<Integer>(), candidates, target, 0);
        return data;
    }

    private static void backtrack(List<Integer> tempList, int[] cand, int target, int start) {

//        if(target == 0 ) {
//            data.add(new ArrayList(tempList));
//            return;
//        }

//        if(target < 0) return;

        int len = cand.length;
        if(start == len) {
            data.add(new ArrayList<>(tempList));
        return;
        }


        for(int i=start;i<len;i++){
            int num = cand[i];
            if(i > start && num == cand[i-1]) continue;
            tempList.add(num);
            backtrack(tempList,cand,target-num,i+1);
            tempList.remove(tempList.size()-1);
        }
    }

    static List<List<Integer>> data3;
    public List<List<Integer>> combinationSum3(int k, int n) {
        int max = n-k;
        int[] nums = new int[max];

        for (int i = 0; i < max; i++) {
            nums[i] = i + 1;
        }

        backtrack3(new ArrayList<>(),nums,n,0,k);
        return data3;
    }

    private static void backtrack3(List<Integer> tempList, int[] cand, int target, int start, int k) {
        if(target < 0) return;
        if(tempList.size() == k){
            if(target == 0) {
                data3.add(tempList);
                return;
            }else return;
        }

        int len = cand.length;
        if(start == len) {
            return;
        }


        for(int i=start;i<len;i++){
            int num = cand[i];
            tempList.add(num);
            backtrack3(tempList,cand,target-num,i+1,k);
            tempList.remove(tempList.size()-1);
        }
    }


    static Map<Character,Boolean> mp;
    static Set<String> ds;
    public static int numTilePossibilities(String tiles) {
        mp =new HashMap<>();
        ds = new HashSet<>();
        numTilePossibilitiesHelper(tiles,"");
        return  ds.size()-1;
    }

    private static void numTilePossibilitiesHelper(String up, String p) {
        if(up.isEmpty() ){
            System.out.println(p);
            ds.add(p);
            return;

        }

        char c = up.charAt(0);
        up = up.substring(1);
        boolean exp = !(mp.containsKey(c) && mp.get(c) && p.isEmpty());


        mp.put(c,true);
        numTilePossibilitiesHelper(up,p);
        if(exp){
            for (int i = 0; i <= p.length(); i++) {
                String l = p.substring(0,i);
                String r = p.substring(i);

                 numTilePossibilitiesHelper(up,l+c+r);
            }
        }

    }

    static int count = 0;
    static List<List<String>> numTileAns;

    public static void traverseN(char theArray[], boolean used[], List<String> data) {
        System.out.println(data);
        count++; // count the number of sub-permutation
        for(int i = 0; i < theArray.length; i++) {
            if( used[i] )
                continue;
            used[i] = true;
            data.add(theArray[i]+"");
            traverseN(theArray, used,data);
            data.remove(data.size()-1);
            used[i] = false;
        }
    }

    public static int numTilePossibilitiesN(String tiles) {
        char theArray[] = tiles.toCharArray();
        numTileAns = new ArrayList<>();
        Arrays.sort(theArray); // sort the array for duplicate removal
        traverseN(theArray, new boolean[theArray.length], new ArrayList<String>());
        return count - 1;
    }



    static List<List<Integer>> srcTarget;
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        srcTarget = new ArrayList<>();

        allPathsSourceTargetHelper(graph,new ArrayList<>(Arrays.asList(0)),0);
        return srcTarget;
    }

    static void allPathsSourceTargetHelper(int[][] graph,List<Integer> data, int index){
        if(index == graph.length-1) {
            srcTarget.add(new ArrayList<>(data));
            return;
        }

        int[] routes =  graph[index];
        for (int i = 0; i < routes.length; i++) {
            data.add(routes[i]);
            allPathsSourceTargetHelper(graph,data,routes[i]);
            data.remove(data.size()-1);
        }

    }


    static boolean[] visited;

    public static int countArrangement(int n) {
        int[] nums = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }
        return permuteUniqueHelper(nums, new ArrayList<>());
    }


//    public List<List<Integer>>permuteUnique(int[] nums){
//        Arrays.sort(nums);
//        visited = new boolean[nums.length];
//        permData = new ArrayList<>();
//        permuteUniqueHelper(nums, new ArrayList<>());
//        return permData;
//    }

    public static int permuteUniqueHelper(int[] nums, List<Integer> carry) {
        if(carry.size() == nums.length){
            return 1;
        }

        int size = carry.size();
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if(visited[i] || (i>0 && nums[i] == nums[i-1] && !visited[i-1])) continue;
            if(!isBf(nums[i],size)) continue;
            carry.add(nums[i]);
            visited[i] = true;
            cnt += permuteUniqueHelper(nums,carry);
            visited[i]= false;
            carry.remove(size);
        }
        return cnt;
    }
//    public static int permuteUniqueHelper(int[] nums, List<Integer> carry, int start) {
//        if(carry.size() == nums.length){
//            if(isBeautiful(carry)) return 1;
//            return 0;
//        }
//
//
//        int size = carry.size();
//        int cnt = 0;
//        for (int i = start; i < nums.length; i++) {
//
//            if(i > 0 && (nums[start] == nums[start-1])) continue;
//
//            carry.add(nums[i]);
//            visited[i] = true;
//            cnt += permuteUniqueHelper(nums,carry,i+1);
//            visited[i]= false;
//            carry.remove(size);
//        }
//        return cnt;
//    }

    private static boolean isBeautiful(List<Integer> nums){
        for (int i = 0; i < nums.size(); i++) {
            var num = nums.get(i);
//            if(num == 0 ) return false;
            if(((num % (i+1)) != 0) && (((i+1) % num) != 0)){
                return false;
            }
        }
            return true;
    }


    private static boolean isBf(int num, int index){

        return ((num % (index)) == 0) || (((index) % num) == 0);
    }






    static boolean isValid(int r,int c,int[][]board){
        return r >=0 && r< board.length && c>=0 && c< board[0].length;
    }

    static boolean[][] goldVisited;
    public static int getMaximumGold(int[][] grid) {
        goldVisited = new boolean[grid.length][grid[0].length];
        int max = Integer.MIN_VALUE;
        int start =0 ,end=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j]>max){
                        max = grid[i][j];
                        start = i;
                        end = j;
                    }
            }
        }
        return maxGoldHelper(grid,start,end,0);
    }

    private static int maxGoldHelper(int[][] grid,int i,int j, int sum){
        if(grid[i][j] == 0 || goldVisited[i][j]){
            return sum;
        }

        goldVisited[i][j] = true;

        int left =0,right =0,up=0,down=0;

        int tmpSum = sum+grid[i][j];

        //up
        if(isValid(i-1,j,grid)){
                up = maxGoldHelper(grid,i-1,j,tmpSum);
        }

        //down
        if(isValid(i+1,j,grid)){
            down  = maxGoldHelper(grid,i+1,j,tmpSum);
        }

        //left
        if(isValid(i,j-1,grid)){
            left = maxGoldHelper(grid,i,j-1,tmpSum);
        }

        //right
        if(isValid(i,j+1,grid)){
            right = maxGoldHelper(grid,i,j+1,tmpSum);
        }

        goldVisited[i][j] = false;

        return Math.max(Math.max(left,right),Math.max(up,down));

    }


    static ListNode masterHead;
    static ListNode head;
    static boolean stop = false;
    public static void reorderList(ListNode head) {
        masterHead = head;
        LeetCode.head = head;
        reorderListHelper(masterHead);

        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }



    private static void reorderListHelper(ListNode node){
        if(node == null) return;
//        if(masterHead == null) return;

        reorderListHelper(node.next);

        if(stop) return;

        if(head == node || head.next == node) {
            node.next = null;
            stop = true;
            return;
        }
        var tmp = head.next;
        head.next = node;
        node.next = tmp;
        head = tmp;
    }


    static int[][] maxSeqMemo;
    public static int maxPossibleIncreasingSeq(int[] nums) {
        int len = nums.length;
        maxSeqMemo = new int[len][len+1];
        for(int[] row:maxSeqMemo)
            Arrays.fill(row,-1);
        return increasingSeqHelper(nums, 0, -1, 0);
    }

    private static int increasingSeqHelper(int[] nums, int start, int prev, int cnt) {
        if (start == nums.length) return 0;
        if(maxSeqMemo[start][prev+1]!=-1){
            System.out.println("used");
            return maxSeqMemo[start][prev+1];
        }
        int max = 0;
        for (int i = start; i < nums.length; i++) {
            int prevVal = prev < 0 ? Integer.MIN_VALUE : nums[prev];
            if (nums[i] > prevVal) {
                max = Math.max(1+ increasingSeqHelper(nums, i + 1, i, cnt + 1), max);
            }
        }
        maxSeqMemo[start][prev+1] = max;
        return max;
    }


    static int i = 0;
    public static boolean parseBoolExpr(String expression) {
        return parseHelper(expression);
    }

    public static boolean parseHelper( String exp){
        char f = exp.charAt(i);
        boolean tPresent = false,fPresent = false;
        i+=2;
        while(exp.charAt(i-1) != ')'){
            if(exp.charAt(i) == 't'){
                tPresent = true;
            } else if (exp.charAt(i) == 'f' ) {
                fPresent = true;
            }else{
                boolean ans = parseHelper(exp);
                if(ans) tPresent = true;
                fPresent = true;
            }
                i+=2;
        }
        i--;

        if(f=='|')return tPresent;// if true present return true
        if(f=='&')return !fPresent;// if false present return false

        //if '!' is there
        if(tPresent)return false;
        return true;

    }

    private static boolean[] logicalNot(boolean[] booleans) {
        return new boolean[]{!booleans[0]};
    }

    private static boolean[] logicalAND(boolean[] booleans) {
        boolean ans = true;

        for (boolean c: booleans){
            ans = ans && c;
        }
        return new boolean[]{ans};
    }

    private static boolean[] logicalOR(boolean[] pExp) {
        boolean ans = false;

        for (boolean c: pExp){
            ans = ans || c;
        }
        return new boolean[]{ans};
    }

    public static String makeLargestSpecial(String s) {
        int cnt =0;
        int start = 0;
        List<String> list = new ArrayList<>();
        for (int j = start; j < s.length(); j++) {
            if(s.charAt(j) == '1'){
                cnt++;
            }else if( s.charAt(j) == '0'){
                cnt--;
            }

            if(cnt == 0){
                list.add("1" + makeLargestSpecial(s.substring(start+1,j)) + "0");
                start = j+1;
            }
        }

        Collections.sort(list,Collections.reverseOrder());
        return String.join("",list);
    }


    static boolean[] visitedPerms;
    static boolean stopPerms = false;
    static String permsAns = "";
    static int cont;
    public static String getPermutation(int n, int k) {
        int[] nums = new int[n];
        cont = k;
        visitedPerms = new boolean[n];

        for (int j = 0; j < n; j++) {
            nums[j] = j+1;
        }

        permsHelper(nums, "", 0);
        return permsAns;
    }

    static void permsHelper(int[] nums, String carry, int index){
        if(index == nums.length) return;
        if(stopPerms) return;
        if(visitedPerms[index]) return;
        if(carry.length() == nums.length){

                if(--cont == 0){
                    permsAns = carry;
                    stopPerms = true;
                }
                return;
        }

            visitedPerms[index] = true;
            permsHelper(nums,carry+Integer.toString(nums[index]),index+1);
            visitedPerms[index] = false;

            permsHelper(nums,carry,index+1);
    }


    public static String getPermutation2(int n, int k) {

        List<String> list = new ArrayList<>(n);
        int[] perms = new int[n+1];
         int j;
        for (j = 0; j < n; j++) {
            if(j == 0) perms[j] = 1;
            else{
                perms[j] = (j) * perms[j-1];
            }
            list.add(Integer.toString(j+1));
        }

        perms[j] = (j) * perms[j-1];

        return getPermHelper(list,k-1,perms).toString();

    }

    private static StringBuilder getPermHelper(List<String> list,int k, int[] perms){
            if(list.isEmpty()) return new StringBuilder();

            int cntPerBucket = perms[list.size()-1];

            int index = k/cntPerBucket;
            return new StringBuilder(list.remove(index)).append(getPermHelper(list,k%cntPerBucket,perms));

    }


    public int calculate(String s) {
        int sign = 1;
        int num = 0;
        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);

            if(Character.isDigit(c)){
                num = num * 10 + c-'0';
            }else if(c == '+' || c == '-'){
                result += num * sign;
                sign = c == '+' ? 1 : -1;
                num = 0;
            }else if(c == '('){
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
            }else{
                    result+= num * sign;
                    result *= stack.pop();
                    result += stack.pop();

                    num = 0;
                    sign = 1;
            }

        }

        return result + num *sign;
    }

    public static int totalNQueens(int n) {
            boolean [][] board = new boolean[n][n];

            return NQueenHelper(board, 0,0,n);
    }

    private static int NQueenHelper(boolean[][] board, int i, int j, int n) {
            if(n == 0) return 1;
            if(j == board[0].length){
                return 0;
            }
            if(i == board.length) {
                return 0;
            }

            if(board[i][j]) return 0;

            int cnt = 0;

            if( isSafe(board,i,j)){
            board[i][j] = true;
                cnt += NQueenHelper(board,i+1,0,n-1);
            board[i][j] = false;
            }



                cnt += NQueenHelper(board,i,j+1,n);

            return cnt;
    }

    private static boolean isValid(int i,int j, boolean[][] board){
        return i >= 0 && i < board.length && j>= 0 && j<board[0].length;
    }

    private static boolean isSafe(boolean[][] board,int i, int j){

        // horizontal
        for (int k = 0; k < board[0].length; k++) {
            if(board[i][k]) return false;
        }
        // vertical
        for (int k = 0; k < board.length; k++) {
            if(board[k][j]) return false;
        }

        for (int k = i,l=j; (k>=0 && l>=0) ; k--,l--) {
            if(board[k][l]) return false;
        }

        for (int k = i,l=j; (l< board[0].length && k>=0) ; k--,l++) {
            if(board[k][l]) return false;
        }

        return true;
    }


    static List<List<String>> nqAns;

    public static List<List<String>> solveNQueens(int n) {
        nqAns = new ArrayList<>();
        NQueenHelper2(new boolean[n][n],0,n,new ArrayList<>());
        return nqAns;
    }

    private static void NQueenHelper2(boolean[][] board, int i, int n,List<String> list) {
        if(n == 0){
            nqAns.add(new ArrayList<>(list));
            return;
        }

        if(i == board.length) {
            return;
        }

        for (int k = 0; k < board[0].length; k++) {
            if(isSafe(board,i,k)){
                board[i][k] = true;
                StringBuilder tmp = new StringBuilder(".".repeat(board.length));
                tmp.setCharAt(k,'Q');
                list.add(tmp.toString());
                NQueenHelper2(board,i+1,n-1,list);
                board[i][k] = false;
                list.remove(list.size()-1);
            }
        }
    }


    static boolean[][] uniquePathsMemo;
    public static int uniquePathsIII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        uniquePathsMemo = new boolean[rows][cols];

        int total = rows * cols;
        int r = 0,c =0;
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                    if(grid[j][k] == -1){
                        total--;
                    }else if(grid[j][k] == 1){
                        r = j;
                        c= k;
                    }

            }
        }

        return uniquePathsHelper(grid,r,c,total);
    }


    private static int uniquePathsHelper(int[][] grid,int i, int j,int total){
//        if(i < 0 || j < 0) return 0;
        int val = grid[i][j];
        if(val == 2){
            if(--total == 0) return 1;
            return 0;
        }

//        if(i == grid.length) return 0;
//        if(j == grid[0].length) return 0;



        if(uniquePathsMemo[i][j]) return 0;

        int cnt = 0;
        if(grid[i][j] != -1){
            uniquePathsMemo[i][j] = true;
            if(isSafe(grid,i+1,j))
                cnt += uniquePathsHelper(grid,i+1,j,total-1);

            if(isSafe(grid,i-1,j))
                cnt += uniquePathsHelper(grid,i-1,j,total-1);

            if(isSafe(grid,i,j+1))
                cnt += uniquePathsHelper(grid,i,j+1,total-1);

            if(isSafe(grid,i,j-1))
                cnt += uniquePathsHelper(grid,i,j-1,total-1);
            uniquePathsMemo[i][j] = false;
        }

        return cnt;
    }

    static boolean isSafe(int[][] grid, int i, int j){
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }


    static Map<String,Boolean> hm;
    static List<String> list;
    static int maxLen ;
    public static List<String> wordBreak(String s, List<String> wordDict) {
            maxLen = s.length();
            hm = new HashMap<>();
            list = new ArrayList<>();

            for (String word: wordDict){
                hm.put(word,true);
            }

            wordBreakHelper(s,new StringBuilder(),0,0,s.length());
            return list;
    }

    private static void wordBreakHelper(String s,StringBuilder p, int i, int j,int size){
                if(size == 0) list.add(p.toString());
                if(j == maxLen) return;

                var val = s.substring(i,j+1);
                var oLen = p.length();

                if(hm.getOrDefault(val,false)){
                    if(!p.isEmpty()){
                        p.append(" ");
//                        oLen++;
                    }
                    wordBreakHelper(s,p.append(val),j+1,j+1, size - val.length());
                }
                p.setLength(oLen);
                wordBreakHelper(s,p,i,j+1,size);

    }


    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < words.length; j++) {
            var m = new HashMap<String,Boolean>();
            for (int i = 0; i < words.length; i++) {
                if(i != j)
                    m.put(words[i],true);
            }
            if(wordBreakHelper2(words[j],new StringBuilder(),0,0,words[j].length(),m)){
                    ans.add(words[j]);
            }
        }

        return ans;
    }

    private static boolean wordBreakHelper2(String s,StringBuilder p, int i, int j,int size,Map<String,Boolean> hm){
        if(size == 0) return true;
        if(j == s.length()) return false;

        var val = s.substring(i,j+1);
        var oLen = p.length();

        boolean ans = false;
        if(hm.getOrDefault(val,false)){
            if(!p.isEmpty()){
                p.append(" ");
//                        oLen++;
            }
            ans = ans || wordBreakHelper2(s,p.append(val),j+1,j+1, size - val.length(),hm);
        }
        p.setLength(oLen);
        return ans || wordBreakHelper2(s,p,i,j+1,size,hm);

    }

}


