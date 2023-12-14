class UnionFind{
	public static int[] arr;
	
	UnionFind(int n){
		arr = new int[n+1];
		init();
	}

	private void init() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
	}

	private int find(int x) {
		if(arr[x] == x)
			return x;
		return arr[x] = find(arr[x]);
	}

	public void merge(int x , int y) {
		x = find(x);
		y = find(y);
		
		if(x != y)
			arr[y] = x;
	}

	public boolean Union(int x , int y){
		if(find(x) == find(y))
			return true;
		return false;
	}
}