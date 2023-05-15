import java.util.ArrayList;

class LCA{
	int N;
	boolean[] visited;
	int[] depth;
	int[][] parent;
	int size;
	ArrayList<Integer>[] tree;
	
	public LCA(int n) {
		N = n;
		size = (int) Math.ceil(Math.log(N+1) / Math.log(2)) + 1;
		visited = new boolean[N+1];
		depth = new int[N+1];
		parent = new int[N+1][size];
		tree = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
	}
	
	public void run() {
		dfs(1,1,1);
//		parent[j][i] = j , 2^i 부모
		for (int i = 1; i < size; i++) {
			for (int j = 1; j <= N; j++) {
				int t = parent[j][i-1];
				parent[j][i] = parent[t][i-1];
			}
		}
	}
	
	public void putEdge(int a , int b) {
		tree[a].add(b);
		tree[b].add(a);
	}
	
	private void dfs(int up , int node, int dep){
//		정점의 깊이와 부모를 설정
        if (visited[node]) 
        	return;
        visited[node] = true;
        depth[node] = dep;
        parent[node][0] = up;
        for (int i = 0; i < tree[node].size(); i++){
            dfs(node, tree[node].get(i), dep+1);
        }
    }
	
//	공통조상의 번호 return;
	public int query(int a , int b) {
//		a가 더 깊은 정점으로 만들어줌
		if(depth[a] < depth[b]) {
			int t = a;
			a = b;
			b = t;
		}
//		높이맞추기 큰수부터 돌면서 2^n승만큼보다 차이나면 그만큼 올려줌
		for (int i = size - 1; i >= 0; i--) {
			if(depth[a] - depth[b] >= (1 << i)) {
				a = parent[a][i];
			}
		}

//		높이를 맞춘후 같으면 바로return
		if(a == b)
			return a;

//		같이 올려줌 큰수부터 돌면서 다르면 그만큼 올려도 아직 모자라기 때문에 올려줘도됨
		for (int i = size - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

		return parent[a][0];
	}
}
