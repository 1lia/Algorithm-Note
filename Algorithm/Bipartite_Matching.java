import java.util.*;

class BipartiteMatching{
	private ArrayList<Integer>[] g;
	private int[] visit;
	private int[] match;
	private int result;
	private int N;

	public BipartiteMatching(int N) {
		this.N = N;
		visit = new int[N+1];
		match = new int[N+1];
		g = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<>();
		}
	}
	
	public void putEdge(int a , int b) {
		g[a].add(b);
	}

	public void run() {
		for (int i = 1; i <= N; i++) {
			if(dfs(i , i)) {
				result++;
			}
		}
	}
	
	public boolean dfs(int v, int state) {
		visit[v] = state;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			int nv = match[next];
			if (nv == 0 || visit[nv] != state && dfs(nv, state)) {
				match[next] = v;
				return true;
			}
		}
		return false;
	}
	
	public void print() {
		System.out.println(result);
	}
}