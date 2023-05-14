import java.util.ArrayDeque;
import java.util.Queue;

// a~z 소문자만 N = 찾아야 하는 문자열 최대길이 
class Aho_Corasick {
	private int N = 100005;
	private int C = 26;
	private int[][] trie = new int[N][C];
	private int[] fail = new int[N];
	private int[] term = new int[N];
	private int piv;

	Aho_Corasick(String[] s) {
		for (int i = 0; i < s.length; i++) {
			int p = 0;
			for (int j = 0; j < s[i].length(); j++) {
				if (trie[p][s[i].charAt(j) - 'a'] == 0)
					trie[p][s[i].charAt(j) - 'a'] = ++piv;
				p = trie[p][s[i].charAt(j) - 'a'];
			}
			term[p] = 1;
		}

		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < C; i++) {
			if (trie[0][i] != 0)
				q.add(trie[0][i]);
		}

		while (!q.isEmpty()) {
			int x = q.poll();
			for (int i = 0; i < C; i++) {
				if (trie[x][i] != 0) {
					int p = fail[x];
					
					while (p != 0 && trie[p][i] == 0)
						p = fail[p];
					
					p = trie[p][i];
					fail[trie[x][i]] = p;
					
					if (term[p] != 0)
						term[trie[x][i]] = 1;
					
					q.add(trie[x][i]);
				}
			}
		}
	}

	public boolean query(String s) {
		int p = 0;

		for (int i = 0; i < s.length(); i++) {
			while (p != 0 && trie[p][s.charAt(i) - 'a'] == 0)
				p = fail[p];
			p = trie[p][s.charAt(i) - 'a'];
			if (term[p] != 0)
				return true;
		}
		return false;
	}

}