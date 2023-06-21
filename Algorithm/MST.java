import java.util.Arrays;

class MST{
	public Node[] g;
	public int N;
	public int M;
	public int cnt;
	public MST(int N , int M){
		this.N = N;
		this.M = M;
		g = new Node[M];
	}
	
	public int run() {
		Arrays.sort(g);
		UnionFind set = new UnionFind(N);
		int result = 0;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(!set.Union(g[i].a , g[i].b)) {
				set.merge(g[i].a , g[i].b);
				result += g[i].cost; 
				cnt++;
			}
			
			if(cnt + 1 == N)
				break;
		}
		
		return result;
	}
	
	public void putNode(int a , int b , int cost) {
		g[cnt++] = new Node(a,b,cost);
	}
	
	class Node implements Comparable<Node>{
		int a;
		int b;
		int cost;
		
		public Node(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	class UnionFind {
		public int[] arr;

		UnionFind(int n) {
			arr = new int[n + 1];
			init();
		}

		private void init() {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i;
			}
		}

		private int find(int x) {
			if (arr[x] == x)
				return x;

			arr[x] = find(arr[x]);
			return arr[x];
		}

		public void merge(int x, int y) {
			x = find(x);
			y = find(y);

			if (x == y)
				return;
			arr[y] = x;
			return;
		}

		public boolean Union(int x, int y) {
			x = find(x);
			y = find(y);

			if (x == y)
				return true;
			return false;
		}
	}
}

