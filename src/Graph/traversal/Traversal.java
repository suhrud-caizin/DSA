package Graph.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Traversal {

    /*
     * time complexity = O(n) + O(2E) = O(n + E) {for undirected graph}
     * */
    public List<Integer> bfs(int V, List<List<Integer>> adj) {
        List<Integer> ans = new ArrayList<>(V);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[V];
        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()){
            var node = queue.poll();
            ans.add(node);
            for (Integer it: adj.get(node)){
                if (!visited[it]){
                    visited[it] = true;
                    queue.add(it);
                }
            }
        }

        return ans;
    }

    /*
     * time complexity = O(n) + O(2E) = O(n + E) {for undirected graph}
     * */
    List<Integer> dfs(List<List<Integer>> graph, int node, boolean[] visited, List<Integer> ans) {
        visited[node] = true;
        ans.add(node);

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, ans);
            }
        }

        return ans;
    }
}
