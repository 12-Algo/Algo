package heemin;

import java.util.*;
import java.io.*;

public class B1141_접두사 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            list.add(input);
        }

        int answer = N;

        Collections.sort(list);

        for (int i = 0; i < N - 1; i++) {
            // System.out.println(list.get(i));

            String now = list.get(i);
            String next = list.get(i + 1);

            if (now.length() > next.length()) {
                if (next.equals(now.substring(0, next.length()))) {
                    answer--;
                }
            } else {
                if (now.equals(next.substring(0, now.length()))) {
                    answer--;
                }
            }
        }
        System.out.println(answer);
    }

}
