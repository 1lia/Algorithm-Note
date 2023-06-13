import java.util.ArrayList;

class Prime{
	private int[] spf;
	private ArrayList<Integer> prime;
	public StringBuilder sb;
	
//	오일러 체
	public void linear_sieve(int n) {
		spf = new int[n+1];
		prime = new ArrayList<>();
		sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			if(spf[i] == 0) {
				prime.add(i);
				spf[i] = i;
			}
			
			for (int j = 0; j < prime.size(); j++) {
				int p = prime.get(j);
				if(i * prime.get(j) > n) break;
				spf[i*p] = p;
				if(i % p == 0) break;
			}
		}
	}
//	소인수분해
	public void factorization(int n) {
		while(n != 1) {
			sb.append(spf[n]).append(' ');
			n /= spf[n];
		}
		sb.append('\n');
	}
}