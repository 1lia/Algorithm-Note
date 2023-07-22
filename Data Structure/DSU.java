class DSU{
	public int[] p;
	public DSU(int N) {
		p = new int[N+1];
		for (int i = 0; i <= N; i++) {
			p[i] = -1;
		}
	}
	
	int find(int n) {
		if(p[n] < 0)
			return n;
		return p[n] = find(p[n]);
	}
	
	void merge(int a , int b) {
		a = find(a);
		b = find(b);
		if (a == b) 
			return;
		
	    if (size(a) > size(b)) {
	    	a ^= b; b ^= a; a ^= b;
	    }
	    
	    p[b] += p[a];
	    p[a] = b;
	}
	boolean union(int a , int b) {
		if(find(a) == find(b)){
			return true;
		}
		return false;
	}

	int size(int n) { 
		return -p[find(n)]; 
	}
}