package heemin;

import java.util.*;
import java.io.*;

public class B1251_단어나누기 {
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        LinkedList<String> list = new LinkedList<>();
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < input.length() - 1; i++) {
            sb.setLength(0);

            for (int j = i + 1; j < input.length(); j++) {

                sb = new StringBuffer(input.substring(0, i)).reverse();
                sb = sb.append(new StringBuffer(input.substring(i, j)).reverse())
                        .append(new StringBuffer(input.substring(j)).reverse());
                list.add(sb.toString());
            }
        }

        Collections.sort(list);

        System.out.println(list.get(0));
    }
}