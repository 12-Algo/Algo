import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    // 집, 치킨집 전부 저장할 수 있는 클래스
    static class House {
        int x, y;
        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 치킨집 저장
    static ArrayList<House> chickenList = new ArrayList<>();
    // 일반 집 저장
    static ArrayList<House> houseList = new ArrayList<>();
    // 살아남은 치킨집 저장
    static ArrayList<House> selectList = new ArrayList<>();
    static int n, m;
    static int[][] map = new int[51][51];
    // 이미 선택된 index의 치킨집
    static boolean[] select = new boolean[14];
    static int answer = 99999999;

    // 맨해튼 거리 계산 함수
    static int calc_dist() {
        int dist_sum = 0;
        for (int i = 0; i < houseList.size(); i++) {
            int x = houseList.get(i).x;
            int y = houseList.get(i).y;
            int dist = 99999999;
            for (int j = 0; j < selectList.size(); j++) {
                int c_x = selectList.get(j).x;
                int c_y = selectList.get(j).y;
                int t_d = Math.abs(c_x - x) + Math.abs(c_y - y);
                dist = Math.min(dist, t_d);
            }
            dist_sum += dist;
        }
        return dist_sum;
    }

    // M개만큼 치킨집 선택 후 거리 계산
    static void dfs(int idx) {
        if(selectList.size() == m) {
            answer = Math.min(answer, calc_dist());
            return;
        }

        for (int i = idx; i < chickenList.size(); i++) {
            if(!select[i]) {
                select[i] = true;
                selectList.add(chickenList.get(i));
                dfs(i);
                select[i] = false;
                selectList.remove(chickenList.get(i));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1)
                    houseList.add(new House(i,j));
                if(temp == 2)
                    chickenList.add(new House(i,j));
                map[i][j] = temp;
            }
        }

        dfs(0);

        System.out.println(answer);
    }
}