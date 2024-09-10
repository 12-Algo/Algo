import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class City implements Comparable<City>{
        int start, dest, cost;

        public City(int start, int dest, int cost) {
            this.start = start;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return this.cost - o.cost;
        }
    }

    static int n, m;
    static ArrayList<City> c_list;
    static int[] unf;
    static int answer = 0;
    static int max_cost = -1;

    static int find(int n) {
        if (unf[n] == n)
            return n;
        else
            return unf[n] = find(unf[n]);
    }

    static void union(int a, int b) {
        int ua = find(a);
        int ub = find(b);
        if (ua != ub)
            unf[ua] = ub;
    }

    static void init() {
        for(int i = 1; i < n + 1; i++) {
            unf[i] = i;
        }
    }

    static void kruskal() {
        for (City city : c_list) {
            if(find(city.start) != find(city.dest)) {
                union(city.start, city.dest);
                answer += city.cost;
                max_cost = Math.max(max_cost, city.cost);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        unf = new int[n + 1];
        c_list = new ArrayList<>();
        init();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            c_list.add(new City(s, d, w));
        }
        Collections.sort(c_list);
        kruskal();
        System.out.println(answer - max_cost);
    }
}
