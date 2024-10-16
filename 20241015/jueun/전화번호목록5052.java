import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main {

	private static final class Node {
		Map<Character, Node> childs;
		boolean isEnd;

		private Node() {
			this.childs = new HashMap<>();
			this.isEnd = false;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			
			sb.append(solve());
		}

		System.out.println(sb);
	}

	private static String solve() throws Exception {
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		
		Node root = new Node();
		
		for (int i = 0; i < N; i++) {
			Node node = root;
			String tel = arr[i];
			for (int j = 0; j < tel.length(); j++) {
				char num = tel.charAt(j);
				if (!node.childs.containsKey(num)) {
					node.childs.put(num, new Node());
				} else if (node.childs.get(num).isEnd) {
					return "NO\n";
				}

				node = node.childs.get(num);
			}

			if (node.childs.isEmpty())
				node.isEnd = true;
			else
				return "NO\n";
		}

		return "YES\n";
	}
}
