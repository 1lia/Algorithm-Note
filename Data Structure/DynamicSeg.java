class DynamicSeg{
    public Node[] tree;
    private int size = 2;

    public DynamicSeg(){
        tree = new Node[6000000];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }
    };

//  seg.update(1,0,1e18,idx,1)
    public void update(int node , long start , long end , long idx , long v){
        if(start == end){
            tree[node].v += v;
            return;
        }

        long mid = (start + end) >> 1;

        if(idx <= mid){
            if (tree[node].l == -1)
                tree[node].l = size++;
            update(tree[node].l, start, mid, idx, v);
        } else{
            if (tree[node].r == -1)
                tree[node].r = size++;
            update(tree[node].r, mid + 1, end, idx, v);
        }
        int idxl = tree[node].l;
        int idxr = tree[node].r;
        long vl = idxl != -1 ? tree[idxl].v : 0;
        long vr = idxr != -1 ? tree[idxr].v : 0;
        tree[node].v = vl + vr;
    }
    public long query(int node, long start, long end, long l, long r){
        if(node == -1)
            return 0;
        if(end < l || r < start)
            return 0;
        if (l <= start && end <= r) {
            return tree[node].v;
        }

        long mid = (start + end) >> 1;
        return query(tree[node].l , start , mid , l , r) + query(tree[node].r , mid + 1 , end , l , r);
    }


    public long tquery(int node, long start, long end, long t){
        if(start == end){
            return start;
        }
        int idxr = tree[node].r;
        long vr = idxr != -1 ? tree[idxr].v : 0;

        long mid = (start + end) >> 1;

        if(vr >= t){
            return tquery(tree[node].r , mid + 1 , end , t);
        } else{
            return tquery(tree[node].l , start , mid , t - vr);
        }
    }

    public static class Node{
        int l , r;
        long v;
        public Node(){
            l = r = -1;
            v = 0;
        }
    }
}