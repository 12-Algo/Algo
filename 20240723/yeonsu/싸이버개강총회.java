import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int start = Integer.parseInt(input.substring(0, 2)) * 100 + Integer.parseInt(input.substring(3, 5));
        int end = Integer.parseInt(input.substring(6, 8)) * 100 + Integer.parseInt(input.substring(9, 11));
        int over = Integer.parseInt(input.substring(12, 14)) * 100 + Integer.parseInt(input.substring(15, 17));
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        int answer = 0;
        while ((input = br.readLine()) != null) {
            if (input.equals("")) break;
            int record = Integer.parseInt(input.substring(0, 2)) * 100 + Integer.parseInt(input.substring(3, 5));
            String name = input.substring(6);
            if (record <= start) {
                dict.put(name, 1);
            } else if (record >= end && record <= over) {
                if (dict.getOrDefault(name, 0) >= 1) {
                    dict.remove(name);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}