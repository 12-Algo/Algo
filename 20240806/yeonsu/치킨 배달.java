import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;    // City size
    private static int M;    // Max number of chicken shops to choose
    private static int[][] arr;
    private static List<Pair> house;
    private static List<Pair> chicken;
    private static boolean[] visit;
    private static int answer;

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        StringTokenizer token = new StringTokenizer(input);
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        // Initialize variables
        chicken = new ArrayList<>();
        house = new ArrayList<>();
        visit = new boolean[100]; // Assuming maximum number of chicken shops is 100
        arr = new int[N][N];
        answer = Integer.MAX_VALUE; // To store the minimum distance

        // Read the city layout
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(token.nextToken());
                if (arr[i][j] == 2) {
                    chicken.add(new Pair(i, j));
                } else if (arr[i][j] == 1) {
                    house.add(new Pair(i, j));
                }
            }
        }

        // Start DFS to select chicken shops
        dfs(0, M);
        System.out.println(answer);
    }

    private static void dfs(int start, int m) {
        if (m == 0) {
            int sum = 0;
            // Calculate the minimum distance for each house to the nearest chicken shop
            for (Pair h : house) {
                int minDist = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visit[j]) {
                        Pair c = chicken.get(j);
                        int dist = Math.abs(h.r - c.r) + Math.abs(h.c - c.c);
                        minDist = Math.min(minDist, dist);
                    }
                }
                sum += minDist;
            }
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(i + 1, m - 1);
                visit[i] = false;
            }
        }
    }

    // Pair class for house and chicken locations
    static class Pair {
        public int r;
        public int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
