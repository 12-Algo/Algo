import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static void combination(int m, List<List<Integer>> res, List<Integer> comb, List<Integer> nums, int idx) {
		if (comb.size() == m) {
			res.add(new ArrayList<>(comb));
		} else {
			for (int i=idx;i<nums.size();i++) {
				int temp = nums.get(i);
				comb.add(temp);
				
				combination(m, res, comb, nums, i+1);
				
				comb.remove(comb.size()-1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer> chickenX = new ArrayList<>();
		List<Integer> chickenY = new ArrayList<>();
		List<Integer> chickenNum = new ArrayList<>();
		
		List<Integer> houseX = new ArrayList<>();
		List<Integer> houseY = new ArrayList<>();
		
		int[][] city = new int[N][N];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == 2) {
					chickenX.add(j);
					chickenY.add(i);
				} else if (city[i][j] == 1) {
					houseX.add(j);
					houseY.add(i);
				}
			}
		}
		
		for (int i=0;i<chickenX.size();i++) {
			chickenNum.add(i);
		}
		
		// 치킨집 M개 조합 추출
		List<List<Integer>> combinationResult = new ArrayList<>();
		combination(M, combinationResult, new ArrayList<>(), chickenNum, 0);
		
		// 조합을 토대로 도시의 치킨 거리 계산
		// 도시의 치킨 거리 = 모든 집의 치킨 거리의 합
		// 집의 치킨 거리는 존재하는 모든 치킨 집과의 거리를 비교해야함.
		
		int min = 987654321;
		
		
		// 조합의 개수만큼 반복 -> 집의 개수 만큼 반복 -> 치킨집 개수 만큼 반복
		for (int i=0;i<combinationResult.size();i++) {
			int[] chickenDistance = new int[houseX.size()];
			for (int j=0;j<chickenDistance.length;j++) {
				chickenDistance[j] = 987654321;
			}
			for (int j=0;j<houseX.size();j++) {
				for (int k=0;k<combinationResult.get(i).size();k++) {
					int distance = Math.abs(chickenX.get(combinationResult.get(i).get(k)) - houseX.get(j)) 
							+ Math.abs(chickenY.get(combinationResult.get(i).get(k)) - houseY.get(j));
					chickenDistance[j] = Math.min(chickenDistance[j], distance);
				}
			}
			int sum = 0;
			for (int j=0;j<chickenDistance.length;j++) {
				sum += chickenDistance[j];
			}
			min = Math.min(min, sum);
		}
		
		System.out.println(min);
		
	}
}
