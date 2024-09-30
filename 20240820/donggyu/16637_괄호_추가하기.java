import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int Len;
	static List<Integer> nums = new ArrayList<>();
	static List<Character> operands = new ArrayList<>();
	static int ans = Integer.MIN_VALUE;
	
	static void dfs(int depth, int sum) {
		if(depth == Len/2) {
			ans = Math.max(ans, sum);
			return;
		}
//		뒤에 괄호 X
		int notPar = calc(operands.get(depth), sum, nums.get(depth+1));
		dfs(depth+1, notPar);
//		뒤에 괄호 O
		if(depth+1 < Len/2) {
			int yesPar = calc(operands.get(depth+1), nums.get(depth+1), nums.get(depth+2));
			int tempResult = calc(operands.get(depth), sum, yesPar);
			dfs(depth+2, tempResult);
		}
	}
	
	static int calc(char op, int n1, int n2) {
		if(op=='+') {
			return n1+n2;
		}
		else if(op=='-') {
			return n1-n2;
		}
		else {
			return n1*n2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Len = Integer.parseInt(bf.readLine());
		
		String input = bf.readLine();
		for(int i=0;i<Len;i++) {
			if(i%2==0) {
				nums.add(input.charAt(i)-'0');
			}
			else {
				operands.add(input.charAt(i));
			}
		}
		
		dfs(0, nums.get(0));
		System.out.println(ans);
		
	}
		

}