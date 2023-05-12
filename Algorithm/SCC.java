import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

class SCC{
	private int V;
	private int cnt;
	private int id;
	private int[] visit;
	private boolean[] fin; 
	private ArrayList<Integer>[] g;
	private Stack<Integer> stack;
	private ArrayList<ArrayList<Integer>> sccArray = new ArrayList<>();

	public SCC(int v) {
		V = v;
		g = new ArrayList[V+1];
		visit = new int[V+1];
		fin = new boolean[V+1];
		stack = new Stack<Integer>();
		sccArray.add(new ArrayList<>());
		for (int i = 1; i <= V; i++) {
			g[i] = new ArrayList<>();
		}
	}
	
	public void putEdge(int s , int e) {
		g[s].add(e);
	}
	
	public void run() {
		for (int i = 1; i <= V; i++) {
			if(visit[i] == 0) {
				find(i);
			}
		}
	}
	
	public int find(int v) {
		visit[v] = ++id;
		int result = visit[v];
		stack.push(v);
		
		for (int i = 0; i < g[v].size(); i++) {
			if(visit[g[v].get(i)] == 0) {
				result = Math.min(result, find(g[v].get(i)));
				
			} else if(!fin[g[v].get(i)]){
				result = Math.min(result, visit[g[v].get(i)]);
			}
		}
		
		if(result == visit[v]) {
			while(!stack.isEmpty()) {
				int a = stack.pop();
				fin[a] = true;
				sccArray.get(cnt).add(a);
				
				if(a == v) {
					break;
				}
			}
			sccArray.add(new ArrayList<>());
			cnt++;
		}
		return result;
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		Pos[] index = new Pos[cnt]; 
//		scc 내부 정렬하고 맨앞 index순으로 전체 정렬
		for (int i = 0; i < cnt; i++) {
			Collections.sort(sccArray.get(cnt));
			index[i] = new Pos(i , sccArray.get(i).get(0));
		}
		Arrays.sort(index);
		
		sb.append(cnt).append('\n');
		for (int i = 0; i < cnt; i++) {
			int idx = index[i].index;
			for (int j = 0; j < sccArray.get(idx).size(); j++) {
				sb.append(sccArray.get(idx).get(j)).append(' ');
			}
			sb.append(-1).append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
	
	static class Pos implements Comparable<Pos>{
		int index;
		int n;
		public Pos(int index, int n) {
			this.index = index;
			this.n = n;
		}
		@Override
		public int compareTo(Pos o) {
			return this.n - o.n;
		}
	}
}