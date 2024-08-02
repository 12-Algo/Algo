
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();
		int n = Integer.parseInt(br.readLine());
		for (int i=0;i<n;i++) {
			String word = br.readLine();
			list.add(word);
		}
		Collections.sort(list, (a ,b) -> b.length() - a.length());
		StringBuilder sb = new StringBuilder();
		int answer = 0;
		for (int i=0;i<list.size();i++) {
			if (set.contains(list.get(i))) continue;
			for (int j=1;j<list.get(i).length() + 1;j++) {
				set.add(list.get(i).substring(0, j));
			}
			answer++;
		}
		System.out.println(answer);
	}	
}