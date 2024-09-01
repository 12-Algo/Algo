import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int answer;
    static boolean pre[][];
    static int[] takes;
    static int num;
    public static void main(String[] args) throws IOException {
        getInput();
        for (int i=0;i<n;i++) {
            int take = 0;
            for (int j=0;j<n;j++) {
                if (pre[i][j]) {
                    take = Math.max(take, takes[j]);
                }
            }
            takes[i] += take;
        }
        Arrays.sort(takes);
        // answer = Arrays.stream(takes).max().orElseThrow();
        // System.out.println(answer);
        System.out.println(takes[takes.length - 1]);
    }

    private static void getInput() throws IOException {
        token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        pre = new boolean[n][n];
        takes = new int[n];
        for (int i = 0; i < n;i++) {
            token = new StringTokenizer(br.readLine());
            takes[i] = Integer.parseInt(token.nextToken());
            num = Integer.parseInt(token.nextToken());
            for (int j=0;j<num;j++) {
                int prenum = Integer.parseInt(token.nextToken());
                pre[i][prenum - 1] = true;
            }
        }
    }

}