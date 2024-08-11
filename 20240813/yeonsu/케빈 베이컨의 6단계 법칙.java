import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean relation[][];
    static int count[][];
    static int from;
    static int answer;
    public static void main(String[] args) throws IOException {
        getInput();

        for (int i=0;i<n;i++) {
            getCnt(i);
        }

        getMinkb();

        System.out.println(answer);
    }


    private static void getMinkb() {
        int maxFriends = Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            int friends = 0;
            for (int j=0;j<n;j++) {
                friends += count[i][j];
            }
            if (friends < maxFriends) {
                maxFriends = friends;
                answer = i + 1;
            }
        }
    }


    private static void getCnt(int from) {
        Queue<Integer> q =  new ArrayDeque<>();
        
        q.add(from);
        int cnt = 1;
        while(!q.isEmpty()) {
            int length = q.size();
            for (int i=0;i<length;i++) {
                int person = q.poll();
                for (int to=0;to<n;to++) {
                    if (relation[person][to] && count[from][to] == 0 && from != to) {
                        count[from][to] = cnt;
                        q.add(to);
                    }
                }
            }
            cnt++;
        }
    }


    private static void getInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(in.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        relation = new boolean[n][n];
        count = new int[n][n];
        for (int i=0;i<m;i++) {
            token = new StringTokenizer(in.readLine());
            int f1 = Integer.parseInt(token.nextToken()) - 1;
            int f2 = Integer.parseInt(token.nextToken()) - 1;

            relation[f1][f2] = true;
            relation[f2][f1] = true;
        }
    }
}
