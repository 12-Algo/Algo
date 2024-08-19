import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static boolean[] selected;
    static String[] input;
    static Deque<String> q;
    static int answer;
    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        input = br.readLine().split("");
        selected = new boolean[n/2];
        answer = Integer.MIN_VALUE;
        findComb(0, 0);
        System.out.println(answer);
    }

    private static void findComb(int depth, int startIdx) {
        //계산
        answer = Math.max(answer, calculate());
        for (int i=startIdx;i<n/2;i++) {
            selected[i] = true;
            findComb(depth+1, i+2);
            selected[i] = false;
        }
        return;
    }

    private static int calculate() {
        q = new ArrayDeque<>();

        //괄호 먼저 계산
        for (int i=0;i<n;i++) {
            if (i % 2 == 1 && selected[i/2]) {
                q.add(String.valueOf(cal(q.pollLast(), input[i], input[i+1])));
                i++;
            }else {
                q.add(input[i]);
            }
        }
        while(q.size() > 1) {
            String a = q.poll();
            String b = q.poll();
            String c = q.poll();
            q.addFirst(String.valueOf(cal(a, b, c)));
        }
        return Integer.parseInt(q.poll());
    }

    private static int cal(String aa, String op, String bb) {
        int a = Integer.parseInt(aa);
        int b = Integer.parseInt(bb);
        if (op.equals("+")) return a + b;
        else if (op.equals("-")) return a - b;
        else return ((a) * (b));
    }

}