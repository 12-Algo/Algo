package heemin;

import java.util.*;
import java.io.*;

public class B1213_팰린드롬만들기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Map<Character, Integer> m = new HashMap<>();
        int len = input.length();

        // 알파벳 개수 세서 맵에 저장
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        ArrayList<Character> list = new ArrayList<>();
        int odd = 0;
        String mid = "";

        for (Map.Entry<Character, Integer> entrySet : m.entrySet()) {

            if (entrySet.getValue() % 2 == 0) {
                for (int i = 0; i < entrySet.getValue() / 2; i++) {
                    list.add(entrySet.getKey());
                }
            } else {
                odd++;
                mid += entrySet.getKey();
                for (int i = 0; i < (entrySet.getValue() - 1) / 2; i++) {
                    list.add(entrySet.getKey());
                }
            }
        }

        if (len % 2 == 0) {
            if (odd > 0) {
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        } else {
            if (odd > 1) {
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        }

        StringBuffer sb = new StringBuffer();

        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        sb.append(mid);

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i));
        }
        System.out.println(sb);
    }
}