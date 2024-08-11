import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] arr;
    static boolean[][] visitWall;   //어디에 벽 세웠는 지 체크!
    static boolean[][] visitRoom;  //어디에 방문했는 지 체크!
    static int roomCount;
    static int answer;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int[][] testData = {{1,2,3,4},{5,6,7,8}, {9,10,11,12}, {13,14,15,16}};

    public static void main(String[] args) throws IOException {
        //입력
        getData();
        //계산
        setThreeWall(0, 0,3);

        //전체 방 수 - (바이러스 퍼진 방 수 + 벽설치 수)
        System.out.println(roomCount - (answer + 3));
    }

    private static void setThreeWall(int startY, int startX, int depth) {
        if (depth == 0) {
            answer = Math.min(answer, getSafeZoneSize());
            return;
        }

        int i = startY;
        int j = startX;
        while (i < n) {
            while (j < m) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    setThreeWall(i, j+1, depth - 1);
                    arr[i][j] = 0;
                }
                j++;
            }
            i++;
            j = 0;
        }
    }

    private static int getSafeZoneSize() {
        int size = 0;
        visitRoom = new boolean[n][m];
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (arr[i][j] == 2 && !visitRoom[i][j]) {
                    size += findEachSize(i, j);
                }
            }
        }
        return size;
    }

    private static int findEachSize(int y, int x) {
        visitRoom[y][x] = true;
        int ret = 1;
        for (int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (checkVisit(ny, nx)) {
                ret += findEachSize(ny, nx);
            }
        }
        return ret;
    }

    private static boolean checkVisit(int ny, int nx) {
        return ny >= 0 && ny < n && nx >= 0 && nx < m && arr[ny][nx] != 1 && !visitRoom[ny][nx];
    }

    private static void getData() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        StringTokenizer token = new StringTokenizer(input);
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        arr = new int[n][m];
        visitWall = new boolean[n][m];
        answer = Integer.MAX_VALUE;

        for (int i=0;i<n;i++) {
            token = new StringTokenizer(in.readLine());
            for (int j=0;j<m;j++) {
                arr[i][j] = Integer.parseInt(token.nextToken());
                if (arr[i][j] != 1) roomCount++;
            }
        }
    }
}
