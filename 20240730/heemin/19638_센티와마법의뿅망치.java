package heemin;

import java.util.*;
import java.io.*;

public class B19638_센티와마법의뿅망치 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            pq.add(h);
        }

        if (pq.peek() < H) {
            System.out.println("YES");
            System.out.println(0);
            return;
        }
        for (int i = 1; i <= T; i++) {
            int pk = pq.peek();
            // System.out.println("pk = " + pk);

            if (pk != 1) {
                pk = pk / 2;
            }

            pq.poll();
            pq.add(pk);

            if (pq.peek() < H) {
                System.out.println("YES");
                System.out.println(i);
                return;
            }
        }

        System.out.println("NO");
        System.out.println(pq.peek());
    }
}