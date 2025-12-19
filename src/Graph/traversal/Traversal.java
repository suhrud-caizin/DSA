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


    public record Pair(int node, int parent) {}

    public boolean isCycle(int n, List<List<Integer>> adj){
        boolean[] visited = new boolean[n+1];

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                if(detectCycleBFS(i, adj, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean detectCycleBFS(int src, List<List<Integer>> adj, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<>();
        visited[src] = true;
        queue.add(new Pair(src, -1));

        while (!queue.isEmpty()){
            var elem = queue.poll();
            for(var neighbour : adj.get(elem.node - 1)){
                if(!visited[neighbour]){
                    visited[neighbour] = true;
                    queue.add(new Pair(neighbour, elem.node));
                } else if (neighbour != elem.parent) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean detectCycleDFS(int src, int parent, List<List<Integer>> adj, boolean[] visited) {
        visited[src] = true;
        for(int neighbour : adj.get(src-1)){
            if(!visited[neighbour]){
                if(detectCycleDFS(neighbour, src, adj, visited)) return true;
            }else if(neighbour != parent){
                return true;
            }
        }
        return false;
    }

    public boolean isCyclicInDirectedGraph(int n, List<List<Integer>> adj) {
        int[] visited = new int[n+1];
        int[] pathVisited = new int[n+1];

        for (int i = 1; i <= n; i++) {
            if(visited[i] == 0){
                if(detectCycleInDirectedDFS(i, adj, visited, pathVisited)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean detectCycleInDirectedDFS(int src, List<List<Integer>> adj, int[] visited, int[] pathVisited) {

        visited[src] = 1;
        pathVisited[src] = 1;

        for (int neighbour : adj.get(src-1)){
            if(visited[neighbour] == 0){
                if(detectCycleInDirectedDFS(neighbour, adj, visited, pathVisited)){
                    return true;
                }
                pathVisited[neighbour] = 0;
            }else if(visited[neighbour] == 1) return true;
        }

        pathVisited[src] = 0;

        return false;
    }
}
