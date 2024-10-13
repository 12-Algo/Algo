import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer token;
    static Set<String> phoneNumbers;
    static Set<String> prefixs;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            phoneNumbers = new HashSet<>();
            prefixs = new HashSet<>();
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                phoneNumbers.add(input);
                for (int j=0;j<input.length() - 1;j++) {
                    prefixs.add(input.substring(0,j+1));
                }
            }
            System.out.println(checkConsistency());
        }
    }

    private static String checkConsistency() {
        for (String phoneNumber : phoneNumbers) {
            if (prefixs.contains(phoneNumber)) {
                return "NO";
            }
        }
        return "YES";
    }

}