import java.util.Arrays;

class Mos{
    int N;
    int Q;
    int div;
    int[] arr;
    int[] cnt;
    int[] sum;
    int[] res;
    Query[] query;

    public void run() throws Exception  {
        StringBuilder sb = new StringBuilder();
        init();
        Arrays.sort(query);

        int s = 1;
        int e = 1;
        int max = 1;
        sum[arr[1]]++;
        cnt[1]++;
        for (int i = 0; i < Q; i++) {
            while(e < query[i].e){
                sum[arr[++e]]++;
                cnt[sum[arr[e]]]++;
                cnt[sum[arr[e]] - 1]--;
                max = Math.max(max , sum[arr[e]]);
            }
            while(e > query[i].e){
                cnt[sum[arr[e]]]--;
                if(max == sum[arr[e]] && cnt[sum[arr[e]]] == 0){
                    max--;
                }
                sum[arr[e]]--;
                cnt[sum[arr[e--]]]++;
            }

            while(s < query[i].s){
                cnt[sum[arr[s]]]--;
                if(max == sum[arr[s]] && cnt[sum[arr[s]]] == 0){
                    max--;
                }
                sum[arr[s]]--;
                cnt[sum[arr[s++]]]++;
            }

            while(s > query[i].s){
                sum[arr[--s]]++;
                cnt[sum[arr[s]]]++;
                cnt[sum[arr[s]] - 1]--;
                max = Math.max(max , sum[arr[s]]);
            }
            res[query[i].idx] = max;
        }
        for (int i = 0; i < Q; i++) {
            sb.append(res[i]).append('\n');
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

    private void init(){
        try {
            N = readInt();
            Q = readInt();
            div = (int) Math.sqrt(N);
            arr = new int[N+1];
            res = new int[Q];
            cnt = new int[100002];
            sum = new int[200002];
            query = new Query[Q];
            for (int i = 1; i <= N; i++) {
                arr[i] = readInt() + 100000;
            }
            for (int i = 0; i < Q; i++) {
                query[i] = new Query(readInt(), readInt() , i);
            }
        } catch (Exception e) {

        }
    }


    class Query implements Comparable<Query>{
        int s;
        int e;
        int idx;
        public Query(int s, int e , int idx) {
            this.s = s;
            this.e = e;
            this.idx = idx;
        }
        @Override
        public int compareTo(Query o) {
            if(this.s / div == o.s / div) {
                return this.e - o.e;
            }
            return this.s - o.s;
        }
    }

    public static int readInt() throws Exception {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
}