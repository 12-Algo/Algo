import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parents;
	static List<Integer> knowTruth;
	static List<List<Integer>> parties;
	
	static int countCanLie() {
		int cnt = 0;
		
		for(List<Integer> party : parties) {
			boolean canLie = true;
			
			for(int person : party) {
				if(find(person) == find(knowTruth.get(0))) {
					canLie = false;
					break;
				}
			}
			if(canLie) {
				cnt++;
			}
		}
		return cnt;
	}
	
	static int find(int x) {
		if(parents[x]==x) 
			return x;
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) {
			parents[y] = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		M = Integer.parseInt(input.nextToken());
		
		parents = new int[N+1];
		knowTruth = new ArrayList<>();
		
		StringTokenizer people = new StringTokenizer(bf.readLine());
		int knowPersonNum = Integer.parseInt(people.nextToken());
		
		if(knowPersonNum == 0) {
			System.out.println(M);
			return;
		}
		
		for(int i=0;i<knowPersonNum;i++) {
			knowTruth.add(Integer.parseInt(people.nextToken()));
		}
		
		parties = new ArrayList<>();
		for(int i=0;i<M;i++	) {
			parties.add(new ArrayList<>());
			StringTokenizer partyInput = new StringTokenizer(bf.readLine());
			int partyCount = Integer.parseInt(partyInput.nextToken());
			for(int j=0;j<partyCount;j++) {
				parties.get(i).add(Integer.parseInt(partyInput.nextToken()));
			}
		}
		
		for(int i=1;i<=N;i++) {
			parents[i] = i;
		}
//		진실을 아는 사람들 한 그룹으로
		for(int i=0;i<knowPersonNum-1;i++){
			union(knowTruth.get(i), knowTruth.get(i+1));
		}
//		파티 인원들을 한 그룹으로
		for(List<Integer> party : parties) {
			int first = party.get(0);
			for(int j=1;j<party.size();j++) {
				union(first, party.get(j));
			}
		}
		System.out.println(countCanLie());
	}
	
}