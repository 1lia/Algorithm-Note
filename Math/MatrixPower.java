class MatrixPower {
	public long[][] matrix;
	private long MOD;
	private int N;

	public MatrixPower(int n, long mod) {
		MOD = mod;
		N = n;
		matrix = new long[N][N];
	}

	long[][] run(long n) {
		if (n == 1)
			return matrix;

		long[][] t = run(n >> 1);

		if ((n & 1) == 0) {
			return power(t, t);
		}
		return power(power(t, t), matrix);
	}

	long[][] power(long[][] a, long[][] b) {
		long[][] t = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					t[i][j] += (a[i][k] * b[k][j]);
					t[i][j] %= MOD;
				}
			}
		}
		return t;
	}
}
