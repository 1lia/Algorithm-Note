import java.util.ArrayList;

class ETT{
    ArrayList<Integer>[] g;
    int[] num;
    int cnt;
    Pos[] et;
    long[] depth;
    boolean[] visit;

    public ETT(int N) {
        g = new ArrayList[N+1];
        num = new int[N+1];
        et = new Pos[N+1];
        depth = new long[N+1];
        visit = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
    }
    public void run(int root){
        dfs(root , 1L);
    }

    private void dfs(int v , long dep){
        visit[v] = true;
        cnt++;
        num[v]=cnt;
        int t=cnt;
        et[t] = new Pos();
        et[t].s=cnt;
        depth[cnt] = dep;
        for (int i = 0; i < g[v].size(); i++) {
            int next = g[v].get(i);
            if(!visit[next])
                dfs(next , dep + 1);
        }
        et[t].e=cnt;
    }

    public void putEdge(int a , int b){
        g[a].add(b);
        g[b].add(a);
    }

    class Pos{
        int s;
        int e;
    }
}