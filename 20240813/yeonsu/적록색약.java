import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] image;
    static int[][] saegyac;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0 , -1, 0, 1};
    static int ans1, ans2;
    public static void main(String[] args) throws IOException {
        getInput();
        getAnswers();
        System.out.println(ans1 + " " + ans2);
    }

    private static void getAnswers() {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                //구역 체크
                if (image[i][j] != 0) {
                    findFieldFromImage(i, j, image[i][j]);
                    ans1++;
                }
                if (saegyac[i][j] != 0) {
                    findFieldFromSaegyac(i, j, saegyac[i][j]);
                    ans2++;
                }
            }
        }
    }    

    private static void findFieldFromSaegyac(int i, int j, int color) {
        saegyac[i][j] = 0;
        for (int k=0;k<4;k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && saegyac[ny][nx] == color) {
                findFieldFromSaegyac(ny, nx, color);
            }
        }
    }

    private static void findFieldFromImage(int i, int j, int color) {
        image[i][j] = 0;
        for (int k=0;k<4;k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && image[ny][nx] == color) {
                findFieldFromImage(ny, nx, color);
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(in.readLine());
        n = Integer.parseInt(token.nextToken());
        image = new int[n][n];
        saegyac = new int[n][n];
        ans1 = 0; ans2 = 0;
        for (int i=0;i<n;i++) {
            String line = in.readLine();
            for (int j=0;j<n;j++) {
                if (line.charAt(j) == 'R') {
                    image[i][j] = 1;
                    saegyac[i][j] = 1;
                }
                else if (line.charAt(j) == 'G') {
                    image[i][j] = 2;
                    saegyac[i][j] = 1;
                }
                else if (line.charAt(j) == 'B') {
                    image[i][j] = 3;
                    saegyac[i][j] = 2;
                }
            }
        }
        
    }
}
