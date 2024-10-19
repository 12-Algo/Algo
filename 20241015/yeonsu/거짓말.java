import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer token;
    private static int N, M, K;
    private static Set<Integer> friendsAlreadyKnowtheTruth; //친구들 정보 저장
    private static int[][] friendToParty;   //몇 번 친구가 몇 번 파티에 참석하는지
    private static Set<Integer>[] partyToFriend;  //몇 번 파티에 누가 참석?
    private static int[] partyListcantLie;

    public static void main(String[] args) throws IOException {
        //입력 받기
        getInput();
        //파티 정보를 순회하면서 친구가 있다면, 그 친구의 친구들까지 모두 순회하면서 파티를 삭제.
        checkCanLie();
        //말해도 되는 파티 정하기
        System.out.println(countCanLie());
    }

    private static int countCanLie() {
        int ret  = 0;
        for (int i=0;i<M;i++) {
            if (partyListcantLie[i] == 0) {
                ret++;
            }
        }
        return ret;
    }

    //거짓말 가능한 파티인지 확인하기.
    private static void checkCanLie() {
        partyListcantLie = new int[M];
        boolean[] visited = new boolean[N + 1]; // 방문체크

        for (int i = 0; i < M; i++) {
            boolean hasTruthKnower = false; // 해당 파티에 진실을 아는 친구가 있는지 확인
            for (int friend : partyToFriend[i]) {
                if (friendsAlreadyKnowtheTruth.contains(friend)) {
                    hasTruthKnower = true;
                    partyListcantLie[i] = 1; // 이 파티는 거짓말을 할 수 없음
                    break;
                }
            }
            if (!hasTruthKnower) continue; // 진실을 아는 친구가 없다면 다음 파티로 넘어감

            // 진실을 아는 친구가 발견되면 DFS 수행
            for (int friend : partyToFriend[i]) {
                if (!friendsAlreadyKnowtheTruth.contains(friend) && !visited[friend]) {
                    dfs(friend, visited); // DFS 호출
                }
            }
        }
    }

    private static void dfs(int friend, boolean[] visited) {
        visited[friend] = true; // 이 친구를 방문한 것으로 표시
        // 이 친구가 참석하는 모든 파티 확인
        for (int i = 0; i < M; i++) {
            if (friendToParty[friend][i] == 1) {
                partyListcantLie[i] = 1; // 이 파티는 거짓말을 할 수 없음
                // 이 파티에 참석하는 모든 친구를 재귀로 확인
                for (int f : partyToFriend[i]) {
                    if (!visited[f]) {
                        dfs(f, visited); // 재귀 호출
                    }
                }
            }
        }
    }




    private static void getInput() throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        //친구 정보 입력 받기
        token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());
        friendsAlreadyKnowtheTruth = new HashSet<>();
        for (int i=0;i<K;i++) {
            friendsAlreadyKnowtheTruth.add(Integer.parseInt(token.nextToken()));
        }

        //파티 정보 입력 받기
        partyToFriend = new Set[M];
        friendToParty = new int[N+1][M];    //파티는 0번부터 시작, 친구 번호는 1번부터 시작.
        for (int i=0;i<M;i++) {
            partyToFriend[i] = new HashSet<>();
        }
        for (int i=0;i<M;i++) {
            token = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(token.nextToken());
            for (int j=0;j<count;j++) {
                int friend = Integer.parseInt(token.nextToken());
                friendToParty[friend][i] = 1;
                partyToFriend[i].add(friend);
            }
        }
    }

}