import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1516 {
    static int n;
    static int[] degree;
    static int[] time;
    static int[] ans_list;
    static ArrayList<Integer>[] list;

    static void T_sort() {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            ans_list[i] = time[i];
            if(degree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int next : list[cur]) {
                degree[next]--;
                ans_list[next] = Math.max(ans_list[next], ans_list[cur] + time[next]);
                if(degree[next] == 0) {
                    q.add(next);
                }
            }
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
            while(temp != -1) {
                list[temp].add(i);
                temp = Integer.parseInt(st.nextToken());
                degree[i]++;
            }
        }
        T_sort();
        for(int i = 1; i <= n; i++) {
            System.out.println(ans_list[i]);
        }
    }
}
