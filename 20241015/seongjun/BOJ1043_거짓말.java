import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 수
		int M = Integer.parseInt(st.nextToken()); // 파티 수
				
		boolean[] whoKnowsTruth = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		int numberOfKnowsTruth = Integer.parseInt(st.nextToken());
		if (numberOfKnowsTruth != 0) {
			for (int i=0;i<numberOfKnowsTruth;i++) {
				whoKnowsTruth[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		List<List<Integer>> graph = new ArrayList<>();
		for (int i=0;i<N+1;i++) {
			graph.add(new ArrayList<>());
		}
		List<Integer>[] partyParticipants = new List[M];
		for (int i=0;i<M;i++) {
			partyParticipants[i] = new ArrayList<>();
		}
		for (int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int numberOfParticipants = Integer.parseInt(st.nextToken());
			for (int i=0;i<numberOfParticipants;i++) {
				partyParticipants[m].add(Integer.parseInt(st.nextToken()));
			}
			
			for (int i=0;i<partyParticipants[m].size();i++) {
				for (int j=i+1;j<partyParticipants[m].size();j++) {
					graph.get(partyParticipants[m].get(i)).add(partyParticipants[m].get(j));
					graph.get(partyParticipants[m].get(j)).add(partyParticipants[m].get(i));
				}
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		for (int i=1;i<N+1;i++) {
			if (whoKnowsTruth[i]) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int knowTruth = q.poll();
			for (int i=0;i<graph.get(knowTruth).size();i++) {
				int connectedWithKnowTruth = graph.get(knowTruth).get(i);
				if (whoKnowsTruth[connectedWithKnowTruth]) {
					continue;
				}
				whoKnowsTruth[connectedWithKnowTruth] = true;
				q.add(connectedWithKnowTruth);
			}
		}
		
		int lieCount = 0;
		for (int m=0;m<M;m++) {
			for (int i=0;i<partyParticipants[m].size();i++) {
				if (!whoKnowsTruth[partyParticipants[m].get(i)]) {
					lieCount++;
					break;
				}
			}
		}
		System.out.println(lieCount);
	}
}