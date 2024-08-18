import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] pan;
    static boolean[][] check;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0 , -1, 0, 1};
    static int countCheese;
    static int answer;
    public static void main(String[] args) throws IOException {
        getInput();
        getAnswers();
        System.out.println(answer + "\n" + countCheese);
    }

    private static void getAnswers() {
        //끝날 때까지 치즈 지우기
        while(true){
            check = new boolean[n][m];
            
            //치즈 개수 확인하고 0개면 종료
            int temp;
            if ((temp = checkAllMelted()) == 0) break;
            else countCheese = temp;

            //치즈 지우기
            findMeltedCheese(0, 0);
            removeCheese();
            
            answer++;
        }
    }

    private static void removeCheese() {
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (check[i][j] == true) {
                    pan[i][j] = 0;
                }
            }
        }
    }

    private static int checkAllMelted() {
        int cnt = 0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (pan[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    /*
     * 치즈에 공기 묻히기
     * check 배열에 사라질 치즈 위치 체크
     */
    private static void findMeltedCheese(int i, int j) {
        for (int dir=0;dir<4;dir++) {
            int ny = i + dy[dir];
            int nx = j + dx[dir];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !check[ny][nx]) {
                check[ny][nx] = true;
                if (pan[ny][nx] == 0) {
                    findMeltedCheese(ny, nx);
                } 
                
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(in.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        pan = new int[n][m];
        check = new boolean[n][m];
        answer = 0;
        for (int i=0;i<n;i++) {
            token = new StringTokenizer(in.readLine());
            for (int j=0;j<m;j++) {
                pan[i][j] = Integer.parseInt(token.nextToken());
            }
        }
    }    
}
