import java.util.*;
class Dijkstra {
    int[][] edges;
    int n;
    List<List<Edge>> graph = new ArrayList<>();
    int[] distancesFromNNode;

    public Dijkstra(int[][] edges, int n) {
        this.edges = edges;
        this.n = n;
        distancesFromNNode = new int[n];
        buildGraph();
    }

    public void buildGraph() {
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }
    }

    public int[] minimumCost(int node) {
        Arrays.fill(distancesFromNNode, Integer.MAX_VALUE);
        distancesFromNNode[node] = 0;
        PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.costFromStart));
        pq.offer(new Path(0, node));
        Set<Integer> visited = new HashSet<>();
        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            int curCost = cur.costFromStart;
            int currentNode = cur.lastNode;
            if (visited.contains(currentNode)) {
                continue;
            }
            distancesFromNNode[currentNode] = Math.min(distancesFromNNode[currentNode], curCost);
            visited.add(currentNode);

            List<Edge> neighbours = graph.get(currentNode);
            for (int i = 0; i < neighbours.size(); i++) {
                Edge e = neighbours.get(i);
                if (visited.contains(e.to)) {
                    continue;
                }
                pq.offer(new Path(curCost + e.weight, e.to));
            }
        }

        return distancesFromNNode;
    }


}

class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

}

class Path {
    int costFromStart;
    int lastNode;

    public Path(int timeFromStart, int lastNode) {
        this.costFromStart = timeFromStart;
        this.lastNode = lastNode;
    }
}