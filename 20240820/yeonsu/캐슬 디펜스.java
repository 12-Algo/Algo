import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, d;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int[][] field;
    static int[][] fieldCopy;
    static boolean[] selected;
    static int answer;
    //0 : 좌, 1 : 위, 2 : 우
    static int[] dy = {0, -1, 0};
    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        getInput();
        //1. 궁수의 위치 조합을 생성
        //2. 조합에 따라서 점수를 계산. 
        findCombination(0, 0);

        System.out.println(answer);
    }

    private static void findCombination(int depth, int startIdx) {
        if (depth == 3) {
            //궁수 위치 찾기 (field 초기화)
            fieldCopy = new int[n][m];
            for (int i = 0; i < n; i++) {
                fieldCopy[i] = Arrays.copyOf(field[i], m);
            }
            answer = Math.max(answer, getScore(n));
            // System.out.println(answer + "\n\n");
        } 

        //궁수 위치 조합 탐색
        for (int i=startIdx;i<m;i++) {
            selected[i] = true;
            findCombination(depth+1, i + 1);
            selected[i] = false;
        }
    }

    private static int getScore(int archerRow) {
        if (archerRow == 0) return 0;
        int count = 0;
        //궁수가 있는 자리에서 calculate 수행
        for (int i=0;i<m;i++) {
            if (selected[i]) {
                if (calculate(archerRow, i)) {
                    // System.out.print(archerRow + " " + i + " ");
                    count++;
                }
            }
        }
        // System.out.println();
        //죽인 적의 위치에 0을 입력 (그렇지 않으면 죽인 적을 다시 공격하게 됨)
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (fieldCopy[i][j] == 2) {
                    fieldCopy[i][j] = 0;
                }
            }
        }
        //적 한 칸 움직임 (적이 내려가는 대신 궁수가 한 칸 위로 올라가도록 구현)
        
        return getScore(archerRow-1) + count;
    }

    private static boolean calculate(int archerRow, int archerCol) {
        //각 궁수마다 가장 거리가 가까운 적 고르기.
        Queue<Integer> yq = new LinkedList<>();
        Queue<Integer> xq = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int dist = 1;       //적과의 거리

        //바로 위부터 탐색 시작
        yq.add(archerRow-1);
        xq.add(archerCol);
        visited[archerRow-1][archerCol] = true;

        //바로 위에 적이 있는 경우
        if (fieldCopy[archerRow-1][archerCol] == 1 && dist <= d) {
            fieldCopy[archerRow-1][archerCol] = 2;
            return true;
        } else if (fieldCopy[archerRow-1][archerCol] == 2 && dist <= d) {
            return false;
        }

        //적을 탐색
        while (!yq.isEmpty() && !xq.isEmpty() && (++dist) <= d) {
            int length = yq.size();
            for (int i=0;i<length;i++) {
                int y = yq.poll();
                int x = xq.poll();
                for (int dir=0;dir<3;dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if (ny >= 0 && ny < n && nx >= 0 && nx < m && !visited[ny][nx]) {
                        //궁수가 이미 잡은 적을 또 공격했을 경우 false리턴
                        if (fieldCopy[ny][nx] == 2) return false;
                        //궁수가 잡은 적은 2로 표시
                        if (fieldCopy[ny][nx] == 1) {
                            fieldCopy[ny][nx] = 2;
                            return true;
                        }
                        visited[ny][nx] = true;
                        yq.add(ny);
                        xq.add(nx);
                    }
                }
            }
        }

        //거리 내에 적이 없다면 false반환
        return false;
    }

    private static void getInput() throws IOException {
        token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        d = Integer.parseInt(token.nextToken());
        field = new int[n][m];
        selected = new boolean[m];
        answer = 0;
        for (int i=0;i<n;i++) {
            token = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++) {
                field[i][j] = Integer.parseInt(token.nextToken());
            }
        }
    }

}