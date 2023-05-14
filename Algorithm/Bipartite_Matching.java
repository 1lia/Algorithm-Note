import java.util.ArrayList;
import java.util.Arrays;

class Bipartite_Matching{
	private boolean[] visit;
	private ArrayList<Integer>[] g;
	private int[] match;
	private int N;
	private int result;

	public Bipartite_Matching(int N) {
		this.N = N;
		visit = new boolean[N+1];
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
			Arrays.fill(visit, false);
			if(dfs(i)) {
				result++;
			}
		}
	}
	
	public boolean dfs(int v) {
		visit[v] = true;
		for (int i = 0; i < g[v].size(); i++) {
			int next = g[v].get(i);
			if(match[next] == 0 || (!visit[match[next]] && dfs(match[next]))) {
				match[next] = v;
				return true;
			}
		}
		return false;
	}
	public int print() {
		return result;
	}
}