import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        StringTokenizer token = new StringTokenizer(input);
        int n = Integer.parseInt(token.nextToken());
        int h = Integer.parseInt(token.nextToken());
        int t = Integer.parseInt(token.nextToken());
        int height;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i=0;i<n;i++) {
            height = Integer.parseInt(in.readLine());
            pq.add(-height);
        }
        int top;
        int count = 0;
        while (!pq.isEmpty() && count < t && -pq.peek() >= h) {
            top = -pq.poll();
            pq.add(top == 1 ? -1 : -(top / 2));
            count++;
        }
        if (count == t && -pq.peek() >= h) {
            System.out.println("NO");
            System.out.println(-pq.peek());
        } else {
            System.out.println("YES");
            System.out.println(count);
        }
    }
}
