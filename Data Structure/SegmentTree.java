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
			init(arr , node << 1 , start , (start + end) >> 1);
			init(arr , (node << 1) + 1 , ((start + end) >> 1) + 1 , end);
			tree[node] = tree[node << 1] + tree[(node << 1) + 1];
		}
	}
	
	void update(int node , int start , int end , int index , long value) {
		if(index < start || end < index)
			return;
		
		if(index == start && end == index) {
			tree[node] = value;
		} else {
			update(node << 1 , start , (start + end) >> 1 , index , value);
			update((node << 1) + 1 , ((start + end) >> 1) + 1 , end , index , value);
			tree[node] = tree[node << 1] + tree[(node << 1) + 1];
		}
	}
	
	long query(int node , int start , int end , int left , int right) {
		if (end < left || right < start)
			return 0;
		else if(left <= start && end <= right) {
			return tree[node];
		} else {
			return query(node << 1 , start , (start + end) >> 1 , left , right) + query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right);
		}
	}
}