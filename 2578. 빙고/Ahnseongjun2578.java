import java.io.*;
import java.util.*;

public class Ahnseongjun2578 {

    static boolean check(int[][] arr) {
        int bingo = 0;
        // 가로 세로
        for (int i=0;i<5;i++) {
            boolean rowCheck = true;
            boolean colCheck = true;
            for (int j=0;j<5;j++) {
                if (arr[i][j] != 0) {
                    rowCheck = false;
                }
                if (arr[j][i] != 0) {
                    colCheck = false;
                }
            }
            if (rowCheck) {
                bingo++;
            }
            if (colCheck) {
                bingo++;
            }
        }

        // 대각
        boolean diagCheck1 = true;
        boolean diagCheck2 = true;
        for (int i=0;i<5;i++) {
            if (arr[i][i] != 0) {
                diagCheck1 = false;
            }
            if (arr[i][5-i-1] != 0) {
                diagCheck2 = false;
            }
        }
        if (diagCheck1) {
            bingo++;
        }
        if (diagCheck2) {
            bingo++;
        }

        if (bingo >= 3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[5][5];
        int[][] remove = new int[5][5];

        for (int i=0;i<5;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<5;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0;i<5;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<5;j++) {
                remove[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                int rem = remove[i][j];
                for (int k=0;k<5;k++) {
                    for (int q=0;q<5;q++) {
                        if (arr[k][q] == rem) {
                            count++;
                            arr[k][q] = 0;
                            if (check(arr)) {
                                System.out.println(count);
                                return;
                            }
                        }
                    }
                }
            }
        }

    }
}