package heemin;

import java.util.*;
import java.io.*;

public class B12891_DNA비밀번호 {

    static int A, C, T, G;

    public static boolean check(Map<Character, Integer> m) {
        if (m.getOrDefault('A', 0) < A) {
            return false;
        }
        if (m.getOrDefault('C', 0) < C) {
            return false;
        }
        if (m.getOrDefault('G', 0) < G) {
            return false;
        }
        if (m.getOrDefault('T', 0) < T) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String input = br.readLine();

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = P - 1;

        Map<Character, Integer> m = new HashMap<>();

        for (int i = left; i <= right; i++) {
            char c = input.charAt(i);
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        int answer = 0;

        if (check(m)) {
            answer++;
        }

        while (left <= S - P - 1) {
            char pop = input.charAt(left);
            left++;
            m.put(pop, m.get(pop) - 1);

            right++;
            char push = input.charAt(right);
            m.put(push, m.getOrDefault(push, 0) + 1);

            if (check(m)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}