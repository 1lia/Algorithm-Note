class MergeSortTree {
	public int[][] tree;

	MergeSortTree(int n) {
		double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		long treesize = Math.round(Math.pow(2, treeh));
		tree = new int[Math.toIntExact(treesize)][];
	}

	int[] init(int[] arr, int node, int start, int end) {
		if (start == end) {
			tree[node] = new int[1];
			tree[node][0] = arr[start];
			return tree[node];
		} else {
			int next = node << 1;
			int mid = (start + end) >> 1;
			int[] ar1 = init(arr, next, start, mid);
			int[] ar2 = init(arr, next + 1, mid + 1, end);
			tree[node] = new int[ar1.length + ar2.length];

			int leftindex = 0;
			int rightindex = 0;
			int cnt = 0;
			while (leftindex < ar1.length && rightindex < ar2.length) {
				if (ar1[leftindex] < ar2[rightindex]) {
					tree[node][cnt++] = ar1[leftindex++];
				} else {
					tree[node][cnt++] = ar2[rightindex++];
				}
			}

			while (leftindex < ar1.length) {
				tree[node][cnt++] = ar1[leftindex++];
			}

			while (rightindex < ar2.length) {
				tree[node][cnt++] = ar2[rightindex++];
			}
			return tree[node];
		}
	}

	int query(int node, int start, int end, int left, int right, int k) {
		if (end < left || right < start)
			return 0;
		else if (left <= start && end <= right) {
			return binarySearch(tree[node], k);
		} else {
			int next = node << 1;
			int mid = (start + end) >> 1;
			return query(next, start, mid, left, right, k) + query(next + 1, mid + 1, end, left, right, k);
		}
	}
	
	
	int binarySearch(int[] arr , int k) {
		if(arr[arr.length-1] <= k) {
			return arr.length;
		}
		int s = 0;
		int e = arr.length - 1;
		int m = 0;
		
		while(s < e) {
			m = ((s + e) >> 1);
			if(arr[m] <= k) {
				s = m + 1;
			} else {
				e = m;
			}
		}
		return s;
	}
}