class SegmentTree{
	private long[] tree;
	
	SegmentTree(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new long[Math.toIntExact(treesize)];
	}
	
	void init(long[] arr , int node , int start , int end) {
		if(start == end)
			tree[node] = arr[start];
		else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			init(arr , next , start , mid);
			init(arr , next + 1 , mid + 1 , end);
			tree[node] = tree[next] + tree[next + 1];
		}
	}
	
	void update(int node , int start , int end , int index , long value) {
		if(index < start || end < index)
			return;
		
		if(index == start && end == index) {
			tree[node] = value;
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			update(next , start , mid , index , value);
			update(next + 1 , mid + 1 , end , index , value);
			tree[node] = tree[next] + tree[next + 1];
		}
	}
	
	long query(int node , int start , int end , int left , int right) {
		if (end < left || right < start)
			return 0;
		else if(left <= start && end <= right) {
			return tree[node];
		} else {
			int mid = (start + end) >> 1;
			int next = node << 1;
			return query(next , start , mid , left , right) + query(next + 1 , mid + 1 , end , left , right);
		}
	}
}