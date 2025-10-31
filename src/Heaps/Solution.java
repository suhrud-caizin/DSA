package Heaps;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        System.out.println(
//        findKthLargest(new int[]{3,2,1,5,6,4},2));

//        System.out.println(reorganizeString("aabb"));
        System.out.println(furthestBuilding(new int[]{4,12,2,7,3,18,20,3,19},10,2));
    }
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (b, a) -> Integer.compare(b[0] * b[0] + b[1] * b[1], a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            queue.offer(point);
        }

        int[][] ans = new int[k][2];

        for (int i = 0; i < k; i++) {
            // var p = ;
            ans[i] = queue.poll();
        }
        return ans;
    }

    public static int findKthLargest(int[] arr, int k) {
       var q = new PriorityQueue<Integer>(Comparator.naturalOrder());

       for (int num: arr){
           q.offer(num);
           if (q.size() > k){
               q.poll();
           }
       }

        return q.poll();
    }

    public static String reorganizeString(String s) {
        Map<Character,Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()){
            freqMap.compute(ch,(c,i)->i == null ?1:++i);
        }
        var entries = freqMap.entrySet();

        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((e2,e1)-> e1.getValue().compareTo(e2.getValue()));

        for (var entry:entries){
            pq.offer(entry);
        }
        StringBuilder ans = new StringBuilder();

        while(pq.size() > 1){
            var val1 = pq.poll();
            var val2 = pq.poll();

            ans.append(val1.getKey()).append(val2.getKey());

            int x = val1.getValue()-1;
            if(x > 0){
                val1.setValue(x);
                pq.offer(val1);
            }

            int y = val2.getValue()-1;
            if(y > 0){
                val2.setValue(y);
                pq.offer(val2);
            }
        }
        var temp = pq.poll();
        if(temp != null){
                if(temp.getValue() > 1) return "";
                return ans.append(temp.getKey()).toString();
        }
        return ans.toString();
    }

    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < heights.length; i++) {

            int diff = heights[i+1] - heights[i];

            if(diff > 0)
                minHeap.offer(diff);

            if(minHeap.size() > ladders){
                bricks  -= minHeap.poll();

                if(bricks < 0) return i;
            }
        }
        return 0;
    }

    public int kthSmallest(int[][] matrix, int k) {
            var pq = new PriorityQueue<Integer>();

            for (int[] row : matrix){
                for (int col : row){
                    pq.add(col);
                }
            }

            int val = -1;
                for (int i = 0; i < k; i++) {
                    val = pq.poll();
                }

                return val;


    }

    public int findCheapestPrice(
            final int n,
            final int[][] flights,
            final int src,
            final int dst,
            final int k
    ) {
        Map<Integer,Integer>[] mapArray = new Map[n];
        Arrays.fill(mapArray,new HashMap<>());

        int[] costs = new int[n];
        Arrays.fill(costs,Integer.MAX_VALUE);

        for (int[] flight: flights) {
            mapArray[flight[0]].put(flight[1],flight[2]);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src,0,0});

        while (!queue.isEmpty()){
            int[] elem = queue.poll();
            int currPos = elem[0];
            int steps = elem[1];
            int cost = elem[2];

            for (var entry:mapArray[currPos].entrySet()){
                int nextPos = entry.getKey();
                int nextCost = entry.getValue();
                int currentCost = nextCost + cost;

                if(steps <= k && currentCost < costs[nextPos]){
                    costs[nextPos] = currentCost;
                    int updatedSteps = steps+1;
                    queue.offer(new int[]{nextPos,updatedSteps, currentCost});
                }
            }
        }

        return costs[dst] != Integer.MAX_VALUE ? costs[dst] : -1;
    }


    record Pair(int num, int freq){}

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freqmap =  new HashMap<>();

        for (int num: nums){
            freqmap.put(num, freqmap.getOrDefault(num,0)+1);
        }

        System.out.println();

            PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(Pair::freq));
//            queue.p
            for(var es : freqmap.entrySet()){
                queue.add(new Pair(es.getKey(),es.getValue()));

                if (queue.size() > k){
                    queue.poll();
                }
            }

            int[] ans = new int[k];
            int i=0;

            while(!queue.isEmpty()){
                ans[i++]=queue.poll().num();
            }

            return ans;

    }
}
