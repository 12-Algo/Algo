import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1939 {
    static class Island implements Comparable<Island>{
        int start, end, cost;

        public Island(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        // 무거운 순 정렬
        @Override
        public int compareTo(Island o) {
            return o.cost - this.cost;
        }
        
    }

    static int n, m;
    static ArrayList<Island> i_list = new ArrayList<>();
    static int factory1, factory2;
    static int[] unf;
    static int answer = 0;
    // 유니온 파인드
    static int Find(int a) {
        if(unf[a] == a) return a;
        else {
            return unf[a] = Find(unf[a]);
        }
    }
    // 유니온
    static void Union(int a, int b) {
        int ua = Find(a);
        int ub = Find(b);
        if(ua != ub) {
            unf[ua] = ub;
        }
    }
    

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        unf = new int[n];
        for(int i = 0; i < n; i++) {
            unf[i] = i;
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            i_list.add(new Island(s, d, w));
            i_list.add(new Island(d, s, w));
        }
        st = new StringTokenizer(br.readLine());
        Collections.sort(i_list);
        factory1 = Integer.parseInt(st.nextToken()) - 1;
        factory2 = Integer.parseInt(st.nextToken()) - 1;
        // 크루스칼
        for(int i = 0; i < i_list.size(); i++) {
            Island temp = i_list.get(i);
            // 두 개가 붙어 있을 때까지 계속 붙여주기
            if(Find(factory1) != Find(factory2)) {
                Union(temp.start, temp.end);
                // 무거운 순이니까 answer 계속 바꿔줌
                answer = temp.cost;
            }
        }
        System.out.println(answer);
    }
}
