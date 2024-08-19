import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static int N;
    private static int[][] arr;

    private static int cntFriends(int idx) {
        // 중복 방지를 위해 친구를 담을 set
        Set<Integer> friends = new HashSet<>();

        for (int i = 0; i < N; i++) {
            // 친구라면 set에 담기
            if (arr[idx][i] == 1) {
                friends.add(i);

                // 친구의 친구 set에 담기
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 1 && j != idx) {
                        friends.add(j);
                    }
                }
            }
        }
        return friends.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == 'Y') {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }

        int res = 0;

        for (int i = 0; i < N; i++) {
            res = Math.max(res, cntFriends(i));
        }

        System.out.println(res);
    }

}