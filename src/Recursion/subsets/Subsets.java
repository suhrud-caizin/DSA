package Recursion.subsets;

import java.util.*;

public class Subsets {

    public static List<String> combinations(String up, String p){
        if(up.isEmpty()) return new ArrayList<>(Arrays.asList(p));

        char c = up.charAt(0);

        var left = combinations(up.substring(1),p+c);
        var right = combinations(up.substring(1),p);

        left.addAll(right);

        return left;
    }

    public static  List<List<Integer>> combinations(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> outer  = new ArrayList<>();
        outer.add(new ArrayList<>());
        int start = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            start = 0;
            if(i > 0 && nums[i] == nums[i-1]){
                start = end +1;
            }
            end = outer.size()-1;
            int n = outer.size();
                for (int j = start; j < n; j++) {
                    var l = new ArrayList<>(outer.get(j));
                    l.add(nums[i]);
                    outer.add(l);
                }
        }

        return outer;
    }

    public static List<String> permutations(String p, String up){
        if(up.isEmpty()){
            return new ArrayList<>(Arrays.asList(p));
        }


        List<String> tmp = new ArrayList<>();
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
                    String f = p.substring(0,i);
                    String s = p.substring(i);
                     tmp.addAll(permutations(f+ch+s,up.substring(1)));
        }
        return tmp;
    }


//    static Map<String,char[]> data = new ArrayList<>(Arrays.asList(
//            new char[]{'a', 'b', 'c'},
//            new char[]{'d', 'e', 'f'},
//            new char[]{'g', 'h', 'i'},
//            new char[]{'j', 'k', 'l'},
//            new char[]{'m', 'n', 'o'},
//            new char[]{'p', 'q', 'r', 's'},
//            new char[]{'t', 'u', 'v'},
//            new char[]{'w', 'x', 'y', 'z'}
//    ));

    static Map<Character, char[]> map = new HashMap<>() {{
                put('2',new char[]{'a', 'b', 'c'});
                put('3', new char[]{'d', 'e', 'f'});
                put('4',new char[]{'g', 'h', 'i'});
                put('5',new char[]{'j', 'k', 'l'});
                put('6',new char[]{'m', 'n', 'o'});
                put('7',new char[]{'p', 'q', 'r', 's'});
                put('8',new char[]{'t', 'u', 'v'});
                put('9',new char[]{'w', 'x', 'y', 'z'});
    }};


    static String[] data = {"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public static List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return new ArrayList<String>();
        var l = new ArrayList<String>();
        helper("",digits, l);
        return l;
    }

    private static void helper(String p, String up, List<String> l){
        if(up.isEmpty()) {
            l.add(p);
            return;
        }

        int digit = up.charAt(0) - '0';

        char[] arr = data[digit-1].toCharArray();
        for (int i = 0; i < arr.length; i++) {
            helper(p+arr[i],up.substring(1),l);
        }
    }

    public static  List<String> diceSum(String up, int target){
        if(target == 0) return new ArrayList<>(Arrays.asList(up));

        var tmp = new ArrayList<String>();
        for (int i = 1; i <= 6 && i <= target ; i++) {
            tmp.addAll(diceSum(up+i,target-i));
        }

        return tmp;
    }

    public static List<String> uniquePaths(String p, int r, int c){
        if(r == 1 && c == 1){
            return new ArrayList<>(Arrays.asList(p));
        }

        var tmp = new ArrayList<String>();

        if(r > 1 && c > 1){
            tmp.addAll(uniquePaths(p+"G",r-1,c-1));
        }

        if(r > 1){
            tmp.addAll(uniquePaths(p+"D",r-1,c));
        }

        if(c > 1){
            tmp.addAll(uniquePaths(p+"R",r,c-1));
        }

        return tmp;
    }


    static int[][] memo;
    public static int uniquePaths(int m, int n) {
        memo = new int[m][n];
        return helper(0,0,m,n);
    }

    private static int helper(int r,int c,int m,int n){
        if(r == m-1 || c == n-1) return 1;
        int cnt = 0;
            if(memo[r][c] != 0){
                cnt = memo[r][c];
            }else{
                cnt = helper(r+1,c,m,n) + helper(r,c+1,m,n);
                memo[r][c] = cnt;
            }

        return cnt;
    }

    public static List<String> uniquePathsAllDirections(String p, int r, int c, boolean[][] maze){
        if(r == maze.length-1 && c == maze[0].length-1){
            return new ArrayList<>(Arrays.asList(p));
        }

        if(maze[r][c]) return new ArrayList<>();

        maze[r][c] = true;
        var tmp = new ArrayList<String>();

        if(r < maze.length-1){
            tmp.addAll(uniquePathsAllDirections(p+"D",r+1,c,maze));
        }

        if(c < maze[0].length-1){
            tmp.addAll(uniquePathsAllDirections(p+"R",r,c+1,maze));
        }

        if(r > 0){
            tmp.addAll(uniquePathsAllDirections(p+"U",r-1,c,maze));
        }

        if(c > 0){
            tmp.addAll(uniquePathsAllDirections(p+"L",r,c-1,maze));
        }

        maze[r][c] = false;
        return tmp;
    }



    public static void uniquePathsAllDirectionsPrint(String p, int r, int c, boolean[][] maze, int[][] matrix, int step){
        if(r == maze.length-1 && c == maze[0].length-1){
            matrix[r][c] = step;
            for (int[] row:matrix){
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            System.out.println("path - "+p);
        }

        if(maze[r][c]) return;

        maze[r][c] = true;
        matrix[r][c] = step;

        if(r < maze.length-1){
           uniquePathsAllDirectionsPrint(p+"D",r+1,c,maze,matrix,step+1);
        }

        if(c < maze[0].length-1){
           uniquePathsAllDirectionsPrint(p+"R",r,c+1,maze,matrix,step+1);
        }

        if(r > 0){
         uniquePathsAllDirectionsPrint(p+"U",r-1,c,maze,matrix,step+1);
        }

        if(c > 0){
            uniquePathsAllDirectionsPrint(p+"L",r,c-1,maze,matrix,step+1);
        }

        maze[r][c] = false;
        matrix[r][c] = 0;
    }


    static boolean[] visited;
    static List<List<Integer>> permData;

    public static List<List<Integer>>permuteUnique(int[] nums){
        visited = new boolean[nums.length];
        permData = new ArrayList<>();
        permuteUniqueHelper(nums, new ArrayList<>(), 0);
        return permData;
    }
    public static void permuteUniqueHelper(int[] nums, List<Integer> carry, int start) {
        if(carry.size() == nums.length){
            permData.add(new ArrayList<>(carry));
            return;
        }

        int size = carry.size();
        for (int i = start; i < nums.length; i++) {

            if((i>0 && nums[start] == nums[start-1])) continue;

            carry.add(nums[i]);
            visited[i] = true;
            permuteUniqueHelper(nums,carry,i+1);
            visited[i]= false;
            carry.remove(size);
        }
    }


}
