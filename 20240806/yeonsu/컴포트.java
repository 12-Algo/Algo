import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        StringTokenizer token = new StringTokenizer(input);
        int n = Integer.parseInt(token.nextToken());
        int z = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());
        int[] disturb = new int[m];
        input = in.readLine();
        token = new StringTokenizer(input);
        for (int i=0;i<m;i++) {
            disturb[i] = Integer.parseInt(token.nextToken());
        }


        boolean flag = false;
        boolean failed;
        for (int i=1;i<1001 && !flag;i++) {
            int field = 1;
            failed = false;
            //최대 n번 실행, n번 이상 돌면 실패
            for (int j = 0; j < n && !flag && !failed; j++) {
                int next_field = (field + i) % (n + 1);
                field = next_field < i ? next_field + 1 : next_field;
                for (int k=0;k<m;k++) {
                    if (disturb[k] == field) {
                        failed = true;
                        break;
                    }
                }
                if (field == z) {
                    flag = true;
                    answer = i;
                }
            }
        }
        if (flag) System.out.println(answer);

    }

}
