import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    // 순열 구하면서 크기 조건 고려
	public static void makePermutation(int idx, int n, String[] operations, int[] arr, ArrayList<int[]> permutations, boolean[] check) {
		if (idx == n) {
			permutations.add(Arrays.copyOf(arr, n));
			return;
		}
		
		int start;
		int end;
		
        // 부등호에 따라 뒤에 올 순열 멤버의 범위 다르게 하기
		if (operations[idx-1].equals("<")) {
			start = arr[idx-1] + 1;
			end = 10;
		} else {
			start = 0;
			end = arr[idx-1];
		}
		
		for (int i = start; i < end; i++) {
			if (!check[i]) {
				check[i] = true;
				arr[idx] = i;
				makePermutation(idx+1, n, operations, arr, permutations, check);
				check[i] = false;
			}
		}
	}
	
    // 배열 -> 문자열 변환
	public static String formatting(int[] permutation) {
		StringBuilder sb = new StringBuilder();
		
		for (int n : permutation) {
			sb.append(n);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int k = Integer.parseInt(br.readLine());
		String[] operations = br.readLine().split(" ");
		ArrayList<int[]> permutations = new ArrayList<>();
		int[] tempArr = new int[k+1];
		boolean[] check = new boolean[10];
		
        // 순열 만들면서 비교가 필요하기 때문에 숫자 넣고 시작
		for (int i = 0; i < 10; i++) {
			tempArr[0] = i;
			check[i] = true;
			makePermutation(1, k+1, operations, tempArr, permutations, check);
			check[i] = false;
		}
		
		int l = permutations.size();
		String[] res = new String[l];
		
		for (int i = 0; i < l; i++) {
			res[i] = formatting(permutations.get(i));
		}
		
		Arrays.sort(res);
		System.out.println(res[l-1] + "\n" + res[0]);
   }

}