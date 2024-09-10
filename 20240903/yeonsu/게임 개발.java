import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int answer;
    static boolean post[][];
    static int[] takes;
    static int[] buildTime;
    static int num;
    public static void main(String[] args) throws IOException {
        getInput();
      	printAnswer();
    }

	private static void printAnswer() {
		Queue<Integer> queue = new ArrayDeque<>();
        for (int i=0;i<n;i++) {
            if (!isPre(i)) {
            	queue.add(i);
                buildTime[i] = takes[i];
            }
        }
        
        while(!queue.isEmpty()) {
        	int length = queue.size();

        	for (int i=0;i<length;i++) {
        		int element = queue.poll();
        		//후행 빌딩을 넣어줌.
        		for (int j=0;j<n;j++) {
        			if (post[element][j]) {		//후행 원소 중에서 
        				post[element][j] = false;		//j가 실행되었으므로 j에 연결된 링크 삭제
                        buildTime[j] = Math.max(buildTime[j], takes[j] + buildTime[element]);
        				if (!isPre(j)) {
        					queue.offer(j);		//선행 원소가 모두 삭제된 원소는 큐에 넣음.
        				}
        			}
        		}
        	}
        }
        
        for (int t : buildTime) {
        	System.out.println(t);
        }

	}
    
    private static boolean isPre(int i) {
    	for (int j=0;j<n;j++) {
    		if (post[j][i]) return true;
    	}
    	return false;
    }

    private static void getInput() throws IOException {
        token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        post = new boolean[n][n];
        takes = new int[n];
        buildTime = new int[n];
        for (int i = 0; i < n;i++) {
            token = new StringTokenizer(br.readLine());
            takes[i] = Integer.parseInt(token.nextToken());
            while (true) {
                int prenum = Integer.parseInt(token.nextToken());
                if (prenum == -1) break;
                post[prenum - 1][i] = true;
            }
        }
    }

}