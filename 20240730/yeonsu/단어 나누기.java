import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String candidate = input;
        int point = 0;
        int start = 0;
        String temp, first = "", second = "", third = "";
        for (int i=start;i<input.length()-2;i++) {
            temp = new StringBuilder(input.substring(start, i+1)).reverse().toString();
            if (temp.compareTo(candidate) < 0) {
                candidate = temp;
                point = i;
            }
        }
        first = candidate;
        start = point + 1;
        candidate = input.substring(start);
        for (int i=start;i<input.length()-1;i++) {
            temp = new StringBuilder(input.substring(start, i+1)).reverse().toString();
            if (temp.compareTo(candidate) < 0) {
                candidate = temp;
                point = i;
            }
        }
        second = candidate;
        start = point + 1;
        third = new StringBuilder(input.substring(start)).reverse().toString();
        System.out.println(first + second + third);
    }
}
