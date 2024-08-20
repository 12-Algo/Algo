import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int max = 0;
	
	static int isScorable(boolean base) {
		if (base) {
			return 1;
		}
		return 0;
	}
	
	static int calculateScore(List<Integer> order, int[][] players) {
		int inning = 1;
		int out = 0;
		int score = 0;
		int idx = 1;
		boolean[] base = new boolean[4];
		while (out < 3 * (players.length-1)) {
			int now = players[inning][order.get(idx)];
			if (now == 0) {
				out++;
				if (out > 0 && out % 3 == 0) {
					base = new boolean[4];
					inning++;
				}
			} else if (now == 1) {
				score += isScorable(base[3]);
				base[3] = base[2];
				base[2] = base[1];
				base[1] = true;
			} else if (now == 2) {
				score += isScorable(base[3]);
				score += isScorable(base[2]);
				base[3] = base[1];
				base[2] = true;
				base[1] = false;
			} else if (now == 3) {
				score += isScorable(base[3]);
				score += isScorable(base[2]);
				score += isScorable(base[1]);
				base[3] = true;
				base[2] = false;
				base[1] = false;
			} else if (now == 4) {
				score += isScorable(base[3]);
				score += isScorable(base[2]);
				score += isScorable(base[1]);
				score++;
				base[3] = false;
				base[2] = false;
				base[1] = false;
			}
			
			if (idx == 9) {
				idx = 1;
			} else {
				idx++;
			}
		}
		return score;
	}
	
	static void permutation(List<Integer> p, boolean[] visit, int[][] players) {
		if (p.size() == 9) {
			List<Integer> order = new ArrayList<>(p);
			order.add(4, 1);
			max = Math.max(max, calculateScore(order, players));			
			return;
		}
		
		for (int i=2;i<=9;i++) {
			if (visit[i]) {
				continue;
			}
			p.add(i);
			visit[i] = true;
			permutation(p, visit, players);
			p.remove(p.size()-1);
			visit[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] players = new int[N+1][10];
		for (int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1;j<=9;j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] visit = new boolean[10];
		List<Integer> p = new ArrayList<>();
		p.add(0);
		permutation(p, visit, players);
		
		System.out.println(max);
	}
}