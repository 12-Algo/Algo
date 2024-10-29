import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] houses;
	
	static int BinarySearch() {
//		최소거리
		int left = 1;
//		최대거리
		int right = houses[N-1] - houses[0];
		int answer = 0;
		
		while(left <= right) {
			int mid = (left+right)/2;
			
			if(canInstall(mid)) {
				answer = mid;
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		return answer;
	}
	
	static boolean canInstall(int distance) {
		int count = 1;
//		마지막 설치 위치
		int last = houses[0];
		
		for(int i=1;i<houses.length;i++) {
//			현재 집과 마지막 설치 집 거리 확인
			if(houses[i] - last >= distance) {
				count++;
				last = houses[i];
			}
		}
		
		return count>=C;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		C = Integer.parseInt(input.nextToken());
		
		houses = new int[N];
		for(int i=0;i<N;i++) {
			houses[i] = Integer.parseInt(bf.readLine());
		}
		
		Arrays.sort(houses);
		System.out.println(BinarySearch());	
	}
}