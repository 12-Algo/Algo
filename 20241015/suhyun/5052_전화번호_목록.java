import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int N;
	private static String[] phoneNumbers;
	private static Node node;
	private static StringBuilder sb = new StringBuilder();
	
	private static class Node {
		boolean isLast;
		Node[] child;
		
		Node() {
			isLast = false;
			child = new Node[10];
		}
	}
	
	private static boolean insert(String phoneNumber) {
		Node now = node;
		
		for (int i = 0; i < phoneNumber.length(); i++) {
			int num = phoneNumber.charAt(i) - '0';
			
			if (now.child[num] == null) {
				now.child[num] = new Node();
			} else if (now.child[num].isLast) {
				return false;
			}
			
			now = now.child[num];
		}
		
		now.isLast = true;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
        	N = Integer.parseInt(br.readLine());
        	phoneNumbers = new String[N];
        	node = new Node();
        	
        	for (int i = 0; i < N; i++) {
        		phoneNumbers[i] = br.readLine();
        	}
        	
        	Arrays.sort(phoneNumbers);
        	boolean isPossible = true;
        	
        	for (int i = 0; i < N; i++) {
        		isPossible = insert(phoneNumbers[i]);
        		
        		if (!isPossible) {
        			break;
        		}
        	}
        	
        	String res = isPossible ? "YES" : "NO";
        	sb.append(res).append("\n");
        }
        
		System.out.println(sb.toString());
	}
}