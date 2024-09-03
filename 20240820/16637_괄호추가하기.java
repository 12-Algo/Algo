import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int max = Integer.MIN_VALUE;
	
	static void bracket(StringBuilder expression, int idx, int n) {
		calculate(expression);
		StringBuilder temp = new StringBuilder(expression);
		for (int i=idx;i<temp.length();i+=2) {
			temp.insert(i-1, '(');
			i++;
			temp.insert(i+2, ')');
			
			bracket(temp, i+5, n);
			temp.replace(i+2, i+3, "");
			temp.replace(i-2, i-1, "");
			i--;
		}
	}
	
	static int calculateBracket(String inBracket) {
		if (inBracket.charAt(1) == '+') {
			return (inBracket.charAt(0)-'0') + (inBracket.charAt(2) - '0');
		} else if (inBracket.charAt(1) == '-') {
			return (inBracket.charAt(0)-'0') - (inBracket.charAt(2) - '0');
		} else {
			return (inBracket.charAt(0)-'0') * (inBracket.charAt(2) - '0');
		}
	}
	
	static void calculate(StringBuilder expression) {
		int num = 0;
		int start = 0;
		if (expression.charAt(0) == '(') {
			num = calculateBracket(expression.substring(1, 4));
			start = 5;
		} else {
			num = expression.charAt(0) - '0';
			start = 1;
		}
		
		for (int i=start;i<expression.length();i++) {
			if (expression.charAt(i) == '+') {
				if (expression.charAt(i+1) == '(') {
					num += calculateBracket(expression.substring(i+2, i+5));
					i+=4;
				} else {
					num += (expression.charAt(i+1) - '0');
					i++;
				}
			} else if (expression.charAt(i) == '-') {
				if (expression.charAt(i+1) == '(') {
					num -= calculateBracket(expression.substring(i+2, i+5));
					i+=4;
				} else {
					num -= (expression.charAt(i+1) - '0');
					i++;
				}
			} else if (expression.charAt(i) == '*') {
				if (expression.charAt(i+1) == '(') {
					num *= calculateBracket(expression.substring(i+2, i+5));
					i+=4;
				} else {
					num *= (expression.charAt(i+1) - '0');	
					i++;
				}
			}
		}
		max = Math.max(max, num);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringBuilder expression = new StringBuilder(br.readLine());
		calculate(expression);
		bracket(expression, 1, n);
		
		System.out.println(max);
		
	}
}