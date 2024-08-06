import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	
	static final int WALL_NUM = 3;
	
	static void combination(List<List<Integer>> res, List<Integer> comb, List<Integer> nums, int idx) {
		if (comb.size() == WALL_NUM) {
			res.add(new ArrayList<>(comb));
		} else {
			for (int i=idx;i<nums.size();i++) {
				int temp = nums.get(i);
				comb.add(temp);
				
				combination(res, comb, nums, i+1);
				
				comb.remove(comb.size()-1);
			}
		}
	}
	
	static int[][] buildWall(List<Integer> comb, int[][] arr, List<Integer> xPos, List<Integer> yPos) {
		int[][] res = new int[arr.length][arr[0].length];
		for (int i=0;i<res.length;i++) {
			res[i] = arr[i].clone();
		}
		
		for (int i=0;i<comb.size();i++) {
			int x = xPos.get(comb.get(i));
			int y = yPos.get(comb.get(i));
			res[y][x] = 1;
		}		
		return res;
	}
	
	static void virus(int[][] arr, int x, int y) {
		for (int i=0;i<4;i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (nx < 0 || nx >= arr[0].length || ny < 0 || ny >= arr.length || arr[ny][nx] == 1 || arr[ny][nx] == 2) {
				continue;
			}
			arr[ny][nx] = 2;
			virus(arr, nx, ny);
		}
		
	}
	
	static int countBlank(int[][] arr) {
		int count = 0;
		for (int i=0;i<arr.length;i++) {
			for (int j=0;j<arr[i].length;j++) {
				if (arr[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 빈 칸의 좌표 미리 저장
		List<Integer> blankXPos = new ArrayList<>();
		List<Integer> blankYPos = new ArrayList<>();
		
		int[][] arr = new int[n][m];
		for (int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<m;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {
					blankXPos.add(j);
					blankYPos.add(i);
				}
			}
		}
		
		// 조합을 생성할 숫자들
		List<Integer> nums = new ArrayList<>();
		for (int i=0;i<blankXPos.size();i++) {
			nums.add(i);
		}
		// 저장해둔 빈칸 순서로 조합 생성
		List<List<Integer>> combinationResult = new ArrayList<>();
		int idx = 0;
		combination(combinationResult, new ArrayList<>(), nums, idx);

		
		int max = 0;
		// 조합을 토대로 벽 3개 생성
		for (int i=0;i<combinationResult.size();i++) {
			int[][] temp = buildWall(combinationResult.get(i), arr, blankXPos, blankYPos);
		
			for (int j=0;j<temp.length;j++) {
				for (int k=0;k<temp[j].length;k++) {
					if (temp[j][k] == 2) {
						virus(temp,k,j);
					}
				}
			}
			
			max = Math.max(max, countBlank(temp));
		}
		System.out.println(max);
	}
}
