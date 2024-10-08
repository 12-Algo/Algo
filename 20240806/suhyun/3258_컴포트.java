import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static boolean isPossible(int n, int z, int k, Set<Integer> hudles) {
		int now = 1 + k;
		
        // 제자리로 돌아오면 종료
		while (now != 1) {
			if (hudles.contains(now)) {
				return false;
			}
			
			if (now == z) {
				return true;
			}
			
			now += k;
			
			if (now > n) {
				now -= n;
			}
		}
		return false;	
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer input = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(input.nextToken());
		int Z = Integer.parseInt(input.nextToken());
		int M = Integer.parseInt(input.nextToken());
		
		StringTokenizer mList = new StringTokenizer(br.readLine());
        // 값이 포함되어 있는지 자주 비교해야 해서 set 사용했어요.
		Set<Integer> hudles = new HashSet<>();
		
		for (int i = 0; i < M; i++) {
			hudles.add(Integer.parseInt(mList.nextToken()));
		}
		
        // 1부터 가능한 interval 확인
		int res = 1;
		
		for (int i = 0; i < Z-1; i++) {
			if (isPossible(N, Z, res, hudles)) {
				break;
			};
			res++;
		}
		System.out.println(res);
	}
}
