class Fermat {
	public static long binomial_fermat(long n , long k){
		long MOD = 1000000007L;
		long A = 1;
		long B = 1;

		for (long i = 2; i <= n; i++) {
			A *= i;
			A %= MOD;
		}

		for (long i = 2; i <= k; i++) {
			B *= i;
			B %= MOD;
		}

		for(long i = 2; i + k <= n; i++){
			B *= i;
			B %= MOD;
		}

		long B2 = 1;
		long p = MOD - 2;
		while(p > 0){
			if (p % 2 != 0){
				B2 *= B;
				B2 %= MOD;
			}

			B *= B;
			B %= MOD;
			p /= 2;
		}
		return (A * B2) % MOD;
	}
}

