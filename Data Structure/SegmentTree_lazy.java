class SegmentTree{
	private long[] tree;
	private long[] lazy;
	
	SegmentTree(int n){
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new long[Math.toIntExact(treesize)];
		lazy = new long[Math.toIntExact(treesize)];
	
	}

	void init(long[] arr , int node , int start , int end){
		if (start == end) {
			tree[node] = arr[start];
		}
		else {
			init(arr, node << 1 , start , (start + end) >> 1); 
			init(arr,(node << 1) + 1 , ((start + end) >> 1) + 1 , end);
			tree[node] = tree[node << 1] +  tree[(node << 1) + 1]; 
		}
	}

//update sum
	void update_range(int node , int start , int end , int left , int right , long value) {
		update_lazy(node , start , end);

		if (left > end || right < start) {
			return;
		} else if(left <= start && right >= end ) {

			tree[node] += (end - start + 1) * value; 

			if(start != end) {
				lazy[node << 1] += value;
				lazy[(node << 1) + 1] += value;
			}
			return;
		}
		update_range(node << 1 , start , (start + end) >> 1 , left , right , value);
		update_range((node << 1) + 1, ((start + end) >> 1) + 1 , end , left , right , value);
		tree[node] =  tree[node << 1] + tree[(node << 1) + 1];
	}

	void update_lazy(int node , int start , int end){
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if(start != end) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	long query(int node , int start , int end , int left , int right) {
		update_lazy(node , start , end);
		if (end < left || right < start)
			return 0;
		else if(left <= start && end <= right) {
			return tree[node];
		} else {
			return query(node << 1 , start , (start + end) >> 1 , left , right) + query((node << 1) + 1 , ((start + end) >> 1) + 1 , end , left , right);
		}
	}
}
