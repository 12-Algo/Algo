package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	static List<Integer> nums = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)); 
	
	static void permutation(int k, List<Integer> perm, List<List<Integer>> result) {
		if (perm.size() == k+1) {
			result.add(new ArrayList<>(perm));
		} else if (perm.size() < k+1) {
			for (int i=0;i<nums.size();i++) {
				int n = nums.get(i);
				perm.add(n);
				nums.remove(i);
				
				permutation(k, perm, result);
				
				perm.remove(perm.size()-1);
				nums.add(i, n);
			}
		}
	}
	
	static boolean isAvailable(List<Integer> nums, String[] inequality) {
		for (int i=0;i<inequality.length;i++) {
			if (inequality[i].equals(">")) {
				if (nums.get(i) < nums.get(i+1)) {
					return false;
				}
			} else if (inequality[i].equals("<")) {
				if (nums.get(i) > nums.get(i+1)) {
					return false;
				}
			}
		}
		return true;
	}
	
	static String findMin(String[] inequality, List<List<Integer>> perm) {
		StringBuilder min = new StringBuilder();
		for (int i=0;i<perm.size();i++) {
			if (isAvailable(perm.get(i), inequality)) {
				for (int j=0;j<perm.get(i).size();j++) {
					min.append(perm.get(i).get(j));
				}
				break;
			}
		}
		return min.toString();
	}
	
	static String findMax(String[] inequality, List<List<Integer>> perm) {
		StringBuilder max = new StringBuilder();
		for (int i=perm.size()-1;i>-1;i--) {
			if (isAvailable(perm.get(i), inequality)) {
				for (int j=0;j<perm.get(i).size();j++) {
					max.append(perm.get(i).get(j));
				}
				break;
			}
		}
		return max.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		String[] inequality = br.readLine().split(" ");
		
		List<List<Integer>> perm= new ArrayList<>(); 
		permutation(k, new ArrayList<>(), perm);

		System.out.println(findMax(inequality, perm));
		System.out.println(findMin(inequality, perm));
	}
}