import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static StringBuilder sb;
    static int N, M;
    static int from, to;
    static List<int[]>[] adjList;
    static boolean[] visited;
    static Integer answer;
    static int[][] preNode;
    public static void main(String[] args) throws IOException {
        getInput();
        primMST();   
        System.out.println(answer);
    }

    //프림 알고리즘 사용.
    private static void primMST() {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> -o[1]));

        //시작점과 연결되어 있는 edge들 모두 연결
        for (int[] e : adjList[from]) {
            pq.offer(e);
            preNode[e[0]][0] = from;
            preNode[e[0]][1] = e[1];
        }
        visited[from] = true;
        //최대 힙에서 하나씩 꺼내면서 방문하지 않은 노드중 가장 중량제한이 널널한 노드 찾아서 방문하기.
        while(!pq.isEmpty()) {
            int[] candidate = pq.poll();
            if (visited[candidate[0]]) continue;
            answer = Math.min(answer, candidate[1]);
            visited[candidate[0]] = true;
            if (candidate[0] == to) break;
            for (int[] e : adjList[candidate[0]]) {
                if (!visited[e[0]]) {
                    preNode[e[0]][0] = candidate[0];
                    preNode[e[0]][1] = e[1];     //다음 갈 노드의 preNode는 현재 노드. 다음 갈 노드의 preNode의 무게는 다음 노드와 전 노드의 사이 중량제한.
                    pq.offer(e);
                }
            }
        }
    }

    
    private static void getInput() throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        visited = new boolean[N];
        adjList = new ArrayList[N];
        answer = Integer.MAX_VALUE;
        preNode = new int[N][2];
        for (int i=0;i<N;i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i=0;i<M;i++) {
            int a, b, c;
            token = new StringTokenizer(br.readLine());
            a = Integer.parseInt(token.nextToken());
            b = Integer.parseInt(token.nextToken());
            c = Integer.parseInt(token.nextToken());
            adjList[a-1].add(new int[] {b-1, c});
            adjList[b-1].add(new int[] {a-1, c});
        }
        token = new StringTokenizer(br.readLine());
        from = Integer.parseInt(token.nextToken()) - 1;
        to = Integer.parseInt(token.nextToken()) - 1;

    }
}