import java.util.ArrayList;
import java.util.PriorityQueue;
class Dijkstra{
	ArrayList<Node>[] g;
	PriorityQueue<Node> q;
	int[] dist;
	public Dijkstra(int N) {
		g = new ArrayList[N+1];
		dist = new int[N+1];	
		for (int i = 0; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
			g[i] = new ArrayList<>();
		}
	}
	
	public int run(int s , int e) {
		q = new PriorityQueue<>();
		q.offer(new Node(s , 0));
		dist[s] = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.v == e) {
				return dist[e];
			}
		
			if(dist[node.v] < node.cost) {
				continue;
			}
			
			for (int i = 0; i < g[node.v].size(); i++) {
				Node nxt = g[node.v].get(i);
				if(dist[nxt.v] > node.cost + nxt.cost) {
					dist[nxt.v] = node.cost + nxt.cost;
					q.offer(new Node(nxt.v , dist[nxt.v]));
				}
			}
		}
		return dist[e];
	}
	
	public void putEdge(int a , int b , int cost) {
		g[a].add(new Node(b , cost));
		g[b].add(new Node(a , cost));
	}
	
	class Node implements Comparable<Node>{
		int v;
		int cost;
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}	
	}
}