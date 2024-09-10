import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] degree;
    static int[] time;
    static int[] ans_list;
    static ArrayList<Integer>[] list;
    static int answer = -1;

    // 위상정렬 함수
    static void T_sort() {
        Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			// 본인 일을 진행하는 데 걸리는 시간
			ans_list[i] = time[i];
			// 차수가 0이면 q에 넣고 일 시작
            if(degree[i] == 0){
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Integer next : list[cur]) {
                // 이전 일을 진행했으니까 차수 1 감소
                degree[next]--;
                // 선행되어야 하는 일중에 가장 오래 걸리는(필수니까) 시간으로 선택
                ans_list[next] = Math.max(ans_list[next], ans_list[cur] + time[next]);
                // 차수가 0이 되면 q에 넣고 일 시작
                if (degree[next] == 0) {
                    q.add(next);
                }
            }
        }
        // 제일 오래 걸리는 일
        for(int i = 1; i <= n; i++) {
            answer = Math.max(answer, ans_list[i]);
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        degree = new int[n + 1];
        time = new int[n + 1];
        list = new ArrayList[n + 1];
        ans_list = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int temp = Integer.parseInt(st.nextToken());
            for(int j = 0; j < temp; j++) {
                list[Integer.parseInt(st.nextToken())].add(i);
                degree[i]++;
            }
        }
        T_sort();
        System.out.println(answer);
    }
}
