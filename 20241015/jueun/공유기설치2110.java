import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static final int MAX = 1_000_000_000;

	static int N, C;
	static int[] loc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		loc = new int[N];

		for (int i = 0; i < N; i++) {
			loc[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(loc);

		System.out.println(binarySearch(0, MAX + 1, C));
	}

	private static int binarySearch(int low, int high, int target) {
		
		while(low+1 < high) {
			int mid = (low+high)/2;
			
			if(check(mid)) {
				low = mid;
			} else {
				high = mid;
			}
		}
		
		return high;
	}
	
	private static boolean check(int min) {
		//1. 탐색하는 값: 인접한 두 공유기 사이의 최대 거리
		
		//2. 탐색 범위: [1, MAX] , (0, MAX+1)
		//2-1. while(low+1 < high) 구현 시에는 열린 범위를 탐색해야 한다.
		
		//3. check(1) == true , check(MAX) == false 가 되도록 탐색
		//3-1. check(1) -> cnt = N
		//3-2. check(MAX) -> cnt = 1;
		//3-3. 엄... TTTT FFFF에서 ... T가 많아져야 하니까 >= 인가 ???
		
		//4. min을 최소로 할 수 없으면.. 공유기 설치해야함.
		//4-1. min을 최소로할 수 있으면.. 공유기 설치 안해도 됨 !!
		
		int cnt = N; //공유기 개수
		
		int prev = loc[0];
		for(int i=1; i<N; i++) {
			int dist = loc[i] - prev;

			if(dist <= min) {
				cnt --;
			} else {
				prev = loc[i];
			}
		}

		return cnt >= C;
	}

}
