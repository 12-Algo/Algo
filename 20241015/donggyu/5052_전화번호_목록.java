import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	static int T, N;
	static StringBuilder sb = new StringBuilder();
	static class Trie{
		Map<Character, Trie> child = new HashMap<>();
		boolean isfinal;
		
		Trie(){}
		
		public void insert(String phoneNumber) {
			Trie trie = this;
			for(int i=0;i<phoneNumber.length();i++) {
				char c = phoneNumber.charAt(i);
				
//				자식 노드에 c 없을 경우에만 추가
				trie.child.putIfAbsent(c, new Trie());
//				자식 노드로 찾아가기
				trie = trie.child.get(c);
				
//				마지막 문자인지 표시
				if(i==phoneNumber.length()-1) {
					trie.isfinal = true;
					return;
				}
			}
		}
		
		public boolean isPrefix(String number) {
//			현재 노드를 시작으로
			Trie node = this;
			for(int i=0;i<number.length();i++) {
				char c = number.charAt(i);
				if(!node.child.containsKey(c)) {
					return false;
				}
				
				node = node.child.get(c);
				
//				누군가의 마지막 문자라면 다른 번호의 접두사
				if(node.isfinal && i<number.length()-1) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		
		for(int i=0;i<T;i++) {
			N = Integer.parseInt(bf.readLine());
			Trie trie = new Trie();
			
			List<String> phoneNumbers = new ArrayList<>();
			for(int j=0;j<N;j++) {
				String number = bf.readLine();
				trie.insert(number);
				phoneNumbers.add(number);
			}
			
			boolean isPrefix = false;
			for(String number : phoneNumbers) {
				if(trie.isPrefix(number)) {
					isPrefix = true;
					break;
				}
			}
			
			if(isPrefix)
				sb.append("NO\n");
			else
				sb.append("YES\n");
		}
		System.out.println(sb.toString());
	}	
}