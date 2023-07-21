import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class ConvexHull{
	private Pos[] arr;
	private int N;

	public ConvexHull(int N) {
		this.N = N;
		arr = new Pos[N];
	}
	
	public void put(long a , long b , int i) {
		arr[i] = new Pos(a,b);
	}
	
	public int run() {
		sortmin();
		sort();
		
		Stack<Integer> s = new Stack<>();
		s.push(0);
		for (int i = 1; i < N; i++) {
			while((s.size() > 1) && (ccw(arr[s.get(s.size() - 2)] , arr[s.peek()] , arr[i]) <= 0) ){
				s.pop();
			}
			s.push(i);
		}
		return s.size();
	}
	
	private void sortmin() {
		Pos p = new Pos(0,0);
		for (int i = 1; i < N; i++) {
			if(arr[i].y < arr[0].y) {
				p = arr[0];
				arr[0] = arr[i];
				arr[i] = p;
			} else if(arr[i].y == arr[0].y) {
				if(arr[i].x < arr[0].x) {
					p = arr[0];
					arr[0] = arr[i];
					arr[i] = p;
				}
			}
		}
	}
	
	private void sort() {
		Arrays.sort(arr, 1, N, new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				int c = ccw(arr[0] , o1 , o2);
				if(c == 1)
					return -1;
				else if(c == -1)
					return 1;
				return (int) ((Math.abs(o1.x) + o1.y) - (Math.abs(o2.x) + o2.y));
			}
		});
	}
	
	private int ccw(Pos A , Pos B , Pos C) {
		long t = 0;
		t = (B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y);
		if(t > 0) 
			return 1;
		else if(t < 0) 
			return -1;
		else 
			return 0;
	}
	
	private class Pos implements Comparable<Pos>{
		public long x;
		public long y;
		Pos(long x , long y){
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pos o) {
			if(this.y < o.y)
				return -1;
			else
				return 1;
		}	
	}
}