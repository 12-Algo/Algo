import java.util.*;
import java.io.*;

public class Main {
	
	static boolean inconsistent=false;
	
	static class Node {
		Node[] arr;
		boolean isPrefix;
		
		public Node() {
			arr = new Node[10];
			isPrefix = false;
		}
	}
	
	static void insert(String s, int index, Node[] arr) {
		int n = s.charAt(index) - '0';
		if (arr[n] == null) {
			arr[n] = new Node();
		}
		if (arr[n].isPrefix) {
			inconsistent = true;
			return;
		}
		if (index == s.length()-1) {
			arr[n].isPrefix = true;
			return;
		}
		insert(s, index+1, arr[n].arr);
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			inconsistent = false;
			Node node = new Node();
			int N = Integer.parseInt(br.readLine());
			String[] input = new String[N];
			for (int n=0;n<N;n++) { 
				input[n] = br.readLine();
			}
			Arrays.sort(input);
			for (int i=0;i<N;i++) {
				insert(input[i], 0, node.arr);
			}
			if (inconsistent) {
				System.out.println("NO");
			} else {
				System.out.println("YES");
			}
		}
	}
}